package com.sailyang.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/28 10:19
 */
public class YangfanApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();

    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    public YangfanApplicationContext(Class configClass){
        this.configClass = configClass;
        //扫描
        if (configClass.isAnnotationPresent(ComponentScan.class)) {

            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            //获取扫描路径 com.sailyang.service
            String path = componentScanAnnotation.value();
            //转化成文件夹路径com/sailyang/service
            path = path.replace(".","/");
            //去输出对应的文件夹里面取类，而不是src下面
            ClassLoader classLoader = YangfanApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File f : files) {
                    String fileName = f.getAbsolutePath();

                    if(fileName.endsWith(".class")) {
                        //看看类上有没有Component的注解，才能判断是不是Bean
                        String classStr = "classes\\";
                        //com/sailyang/service
                        String classPath = fileName.substring(fileName.indexOf(classStr) + classStr.length(),fileName.indexOf(".class"));
                        //com.sailyang.service
                        classPath = classPath.replace("\\", ".");
                        try {
                            //入参:com.sailyang.service.
                            Class<?> clazz = classLoader.loadClass(classPath);
                            if (clazz.isAnnotationPresent(Component.class)) {

                                //生成后置处理器的Bean
                                if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                                    BeanPostProcessor instance = null;
                                    try {
                                        instance = (BeanPostProcessor) clazz.newInstance();
                                        beanPostProcessorList.add(instance);
                                    } catch (InstantiationException | IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                                Component component = clazz.getAnnotation(Component.class);
                                String beanName = component.value();

                                //防止为空，这里用类名生成小写的Bean名称 OrderService -> orderService
                                if("".equals(beanName)){
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }

                                // 这里不直接生成Bean，多例是被调用才生成
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);
                                //区分单例还是多例
                                if(clazz.isAnnotationPresent(Scope.class)){
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                }else{
                                    beanDefinition.setScope("singleton");
                                }

                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        //将单例Bean放入单例池
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) {
            throw new NullPointerException();
        }
        String scope = beanDefinition.getScope();
        if("singleton".equals(scope)){
            Object bean = singletonObjects.get(beanName);
            if(null == bean) {
                singletonObjects.put(beanName,createBean(beanName, beanDefinition));
            }
            return bean;
        }else{
            return createBean(beanName, beanDefinition);
        }
    }

    public Object createBean(String beanName,BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        try {
            Object instance = clazz.getConstructor().newInstance();
            //依赖注入
            for (Field f : clazz.getDeclaredFields()) {
                if(f.isAnnotationPresent(Autowired.class)){
                    f.setAccessible(true);
                    f.set(instance, getBean(f.getName()));
                }
            }

            //Aware: 实现回调，对象强转为接口类型
            if(instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

            //后置处理器：初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            //初始化
            if(instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }

            //后置处理器：初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}

package com.sailyang.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
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
    public YangfanApplicationContext(Class configClass){
        this.configClass = configClass;
        //扫描
        if (configClass.isAnnotationPresent(ComponentScan.class)) {

            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();//获取扫描路径 com.sailyang.service
            path = path.replace(".","/"); //转化成文件夹路径com/sailyang/service
            //去输出对应的文件夹里面取类，而不是src下面
            ClassLoader classLoader = YangfanApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File f : files) {
                    String fileName = f.getAbsolutePath();
                    System.out.println(fileName);

                    if(fileName.endsWith(".class")) {
                        //看看类上有没有Component的注解，才能判断是不是Bean
                        String classStr = "classes";
                        //com/sailyang/service
                        String classPath = fileName.substring(fileName.indexOf(classStr + classStr.length()));
                        //com.sailyang.service
                        classPath = classPath.replace("\\", ".");
                        try {
                            //入参:com.sailyang.service.
                            Class<?> clazz = classLoader.loadClass(classPath);
                            if (clazz.isAnnotationPresent(Component.class)) {

                                Component component = clazz.getAnnotation(Component.class);
                                String beanName = component.value();

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
        return null;
    }

}

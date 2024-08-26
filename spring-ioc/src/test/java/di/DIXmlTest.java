package di;

import com.sailyang.di.xml.OrangeJuiceConstructorDI;
import com.sailyang.di.xml.OrangeJuiceSetterDI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/19 17:01
 */
public class DIXmlTest {
    public static void main(String[] args) {
        // 1. 初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2. 通过容器获取实例对象，getBean()方法中的参数是bean标签中的id
        OrangeJuiceConstructorDI orangeJuiceConstructor = (OrangeJuiceConstructorDI) applicationContext.getBean("orangeJuiceConstructorDI");
        // 3. 调用实例中的方法
        orangeJuiceConstructor.needOrangeJuice();

        OrangeJuiceSetterDI orangeJuiceSetter = (OrangeJuiceSetterDI) applicationContext.getBean("orangeJuiceSetterDI");
        orangeJuiceSetter.needOrangeJucie();
    }
}

import com.sailyang.di.annotation.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/19 18:30
 */
public class DIAnnotationTest {
    public static void main(String[] args) {
        // 1. 初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2. 通过容器获取实例对象
        PersonService personService = (PersonService) applicationContext.getBean("personService");
        personService.add();
    }
}

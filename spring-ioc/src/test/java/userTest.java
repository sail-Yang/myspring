import com.sailyang.dao.impl.UserDaoMysqlImpl;
import com.sailyang.dao.impl.UserDaoOrcaleImpl;
import com.sailyang.service.UserService;
import com.sailyang.service.impl.UserServiceImpl;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/7/9 21:10
 */
public class userTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoOrcaleImpl());
        userService.getUser();
    }
}

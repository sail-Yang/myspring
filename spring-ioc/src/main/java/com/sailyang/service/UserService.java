package com.sailyang.service;

import com.sailyang.dao.UserDao;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/7/9 21:08
 */
public interface UserService {

    void setUserDao(UserDao userDao);

    void getUser();
}

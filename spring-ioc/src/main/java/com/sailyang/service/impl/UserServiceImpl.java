package com.sailyang.service.impl;

import com.sailyang.dao.UserDao;
import com.sailyang.dao.impl.UserDaoImpl;
import com.sailyang.dao.impl.UserDaoMysqlImpl;
import com.sailyang.dao.impl.UserDaoOrcaleImpl;
import com.sailyang.service.UserService;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/7/9 21:08
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}

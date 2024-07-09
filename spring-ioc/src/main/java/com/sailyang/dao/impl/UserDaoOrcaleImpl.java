package com.sailyang.dao.impl;

import com.sailyang.dao.UserDao;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/7/9 21:11
 */
public class UserDaoOrcaleImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("使用Orcale获取用户数据");
    }
}

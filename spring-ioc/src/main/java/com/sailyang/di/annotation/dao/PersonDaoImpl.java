package com.sailyang.di.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/19 18:29
 */
@Repository("personDao")
public class PersonDaoImpl implements PersonDao{

    @Override
    public void add() {
        System.out.println("dao层的add方法执行");
    }
}

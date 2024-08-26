package com.sailyang.di.annotation.service;

import com.sailyang.di.annotation.dao.PersonDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/19 18:23
 */
@Service("personService")
public class PersonServiceImpl implements PersonService{
    @Resource(name="personDao")
    private PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    @Override
    public void add() {
        System.out.println("Service的add方法执行");
        personDao.add();
    }
}

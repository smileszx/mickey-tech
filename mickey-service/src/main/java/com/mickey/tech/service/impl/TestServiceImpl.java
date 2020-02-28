package com.mickey.tech.service.impl;

import com.mickey.tech.manager.TestManager;
import com.mickey.tech.orm.entity.User;
import com.mickey.tech.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suzhengxiao
 * @date 2020/1/30 23:28
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestManager testManager;


    @Override
    public User findUserById(Long userId) {
        return testManager.selectById(userId);
    }
}

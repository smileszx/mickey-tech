package com.mickey.tech.manager.impl;

import com.mickey.tech.manager.TestManager;
import com.mickey.tech.orm.entity.User;
import com.mickey.tech.orm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suzhengxiao
 * @date 2020/1/30 23:26
 **/
@Service
public class TestManagerImpl implements TestManager {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }
}

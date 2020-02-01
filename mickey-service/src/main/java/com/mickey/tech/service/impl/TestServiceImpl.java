package com.mickey.tech.service.impl;

import com.mickey.tech.manager.TestManager;
import com.mickey.tech.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suzhengxiao
 * @date 2020/1/30 23:28
 **/
public class TestServiceImpl implements TestService {

    @Autowired
    private TestManager testManager;


}

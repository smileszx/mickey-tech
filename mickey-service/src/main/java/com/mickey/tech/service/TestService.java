package com.mickey.tech.service;

import com.mickey.tech.orm.entity.User;

/**
 * @author suzhengxiao
 * @date 2020/1/30 23:27
 **/
public interface TestService {

    User findUserById (Long userId);

    Integer insert (User user) throws Exception;
}

package com.mickey.tech.manager;

import com.mickey.tech.orm.entity.User;

/**
 *
 * @author suzhengxiao
 * @date 2020/1/30 23:26
 **/
public interface TestManager {

    User selectById (Long id);

    Integer insert (User user);
}

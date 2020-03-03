package com.mickey.tech.orm.mapper;

import com.mickey.tech.orm.MyMapper;
import com.mickey.tech.orm.entity.User;
import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper extends MyMapper<User> {
}
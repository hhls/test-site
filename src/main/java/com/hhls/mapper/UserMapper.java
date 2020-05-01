package com.hhls.mapper;

import com.hhls.pojo.User;
import java.util.List;

public interface UserMapper {

    List<User> readAll();

    List<User> findById(String id);

    Integer insert(User user);

    Integer deleteById(String id);

    Integer updateById(User user);
}

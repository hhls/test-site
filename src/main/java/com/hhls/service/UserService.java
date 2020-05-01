package com.hhls.service;

import com.hhls.pojo.User;
import java.util.List;

public interface UserService {

    List<User> readAll();

    List<User> findById(String id);

    Integer insert(User user);

    Integer deleteById(String id);

    Integer updateById(User user);
}

package com.hhls.service.impl;

import com.hhls.mapper.UserMapper;
import com.hhls.pojo.User;
import com.hhls.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> readAll() {
        return userMapper.readAll();
    }

    @Override
    public List<User> findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteById(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer updateById(User user) {
        return userMapper.updateById(user);
    }

}

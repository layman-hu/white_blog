package com.white.service;

import com.white.api.TestService;
import com.white.entity.User;
import com.white.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
//    @Override
//    public String test() {
//        return "hello world";
//    }
    @Autowired
    private TestMapper testMapper;

    @Override
    public User getUserById(Integer id) {
        return testMapper.getUserById(id);
    }
}

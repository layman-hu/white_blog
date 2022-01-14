package com.white.service;

import com.white.api.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "hello world";
    }
}

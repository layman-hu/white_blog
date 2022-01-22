package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.User;

public interface TestService extends IService<User> {
//    String test();
    User getUserById(Integer id);
}

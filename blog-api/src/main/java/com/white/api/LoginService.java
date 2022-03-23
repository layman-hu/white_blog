package com.white.api;

import com.white.entity.User;

import java.util.HashMap;

public interface LoginService {

    HashMap<String, Object> login(User user);

    Boolean logout();
}

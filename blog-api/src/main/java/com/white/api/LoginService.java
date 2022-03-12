package com.white.api;

import com.white.entity.User;

import java.util.HashMap;

public interface LoginService {

    HashMap<String, String> login(User user);

    Boolean logout();
}

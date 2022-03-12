package com.white.service;

import com.white.api.LoginService;
import com.white.domain.LoginUser;
import com.white.domain.ResultInfo;
import com.white.entity.User;
import com.white.utils.JwtUtil;
import com.white.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public HashMap<String, String> login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证未通过，给出提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }

        //如果认证通过，使用userId生成JWT JWT存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整用户信息存入redis   userId作为key
        redisCache.setCacheObject("login:"+userId,loginUser);

        return map;
    }

    @Override
    public Boolean logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();

        String userId = principal.getUser().getId().toString();
        redisCache.deleteObject("login:"+userId);
        return true;
    }
}

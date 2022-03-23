package com.white.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.white.domain.LoginUser;
import com.white.entity.Role;
import com.white.entity.User;
import com.white.mapper.RoleMapper;
import com.white.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(queryWrapper);

        //若为空，则抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //查询对应权限
//        List<String> list = new ArrayList<>(Arrays.asList("user","test","admin"));
        List<String> permissions = new ArrayList<>();

        List<Role> roles = roleMapper.getRoleListByUsername(username);
        for(Role role:roles){
            permissions.add(role.getRoleName());
        }

        //把数据封装成UserDetails返回
        return new LoginUser(user,permissions);
    }
}

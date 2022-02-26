package com.white.auth;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userService.getOne(wrapper);

        Logger logger = Logger.getLogger("com.white.auth.MyUserDetailsService");
        logger.info("MyUserDetailsService:");
        logger.info("user:\t" + user);
        logger.info("");
        if(user == null){
//            logger.info("该用户不存在");
            throw new UsernameNotFoundException("该用户不存在");
        }


        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUser(user);
//        userDetails.setUsername("admin");
//        userDetails.setPassword("{noop}123456");//{noop}未加密

        userDetails.setUsername(user.getUsername());
        userDetails.setPassword("{noop}" + user.getPassword());//{noop}未加密

//        userDetails.setAuthorities();


        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("TEST");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}

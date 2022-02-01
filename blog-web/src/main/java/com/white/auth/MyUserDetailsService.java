package com.white.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername("admin");
        userDetails.setPassword("{noop}123456");//{noop}未加密
//        userDetails.setAuthorities();

        return userDetails;
    }
}

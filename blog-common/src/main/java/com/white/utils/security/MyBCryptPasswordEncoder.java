//package com.white.utils.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * @author Administrator
// */
//
//@Component
//public class MyBCryptPasswordEncoder implements PasswordEncoder {
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return bCryptPasswordEncoder.encode(rawPassword);
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//    @Bean
//    BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}

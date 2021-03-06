//package com.white.auth;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.white.domain.LoggerInfo;
//import com.white.api.UserService;
//import com.white.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
////        wrapper.eq("userName",username);
//        wrapper.eq("USERNAME",username);
//        User user = userService.getOne(wrapper);
//
//
//        LoggerInfo.initialization()
//                .loggerName("com.white.auth.MyUserDetailsService")
//                .messages("MyUserDetailsService:")
//                .messages("user:\t" + user)
//                .output();
//
//        //查找到对应的用户数据
//        if(user == null){
//            throw new UsernameNotFoundException("该用户不存在");
//        }
//
//        //获取用户角色
//        List<String> roles = userService.getUserRolesByUserId(user.getId());
//        if(roles.size() <= 0){
//            LoggerInfo.initialization()
//                    .loggerName("com.white.auth.MyUserDetailsService")
//                    .messages("MyUserDetailsService:")
//                    .messages("获取的用户角色为空，检查是否存在错误")
//                    .output();
//        }
//
//        MyUserDetails userDetails = new MyUserDetails();
//        userDetails.setUser(user);
//        userDetails.setUsername(user.getUsername());
//        //{noop}未加密
//        userDetails.setPassword("{noop}" + user.getPassword());
//
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        SimpleGrantedAuthority authority;
//        for(String role:roles){
//            authority= new SimpleGrantedAuthority(role);
//            authorities.add(authority);
//        }
//        userDetails.setAuthorities(authorities);
//        return userDetails;
//    }
//}

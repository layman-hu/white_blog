//package com.white.authority;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//public class MyAccessDecisionManager implements AccessDecisionManager {
//    private boolean supports = true;
//
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        //如果，没有需要的权限信息直接放行
//        if(configAttributes == null){
//            return;
//        }
//        if (!authentication.isAuthenticated()){
//            throw new AccessDeniedException("权限不足");
//        }
//
//        //获取登录用户的权限信息
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        for (GrantedAuthority grantedAuthority:authorities){
//            //用户的角色信息
//            String authority = grantedAuthority.getAuthority();
//            //再获取需要的权限信息
//            for (ConfigAttribute configAttribute:configAttributes){
//                if (authority.equals(configAttribute.getAttribute())){
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("权限不足");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return supports;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return supports;
//    }
//}

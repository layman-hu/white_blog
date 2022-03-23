//package com.white.authority;
//
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//
//@Component
//public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//    private boolean support = true;
//    /**
//     * 返回被拦截路径需要的权限信息
//     * @param o
//     * @return
//     * @throws IllegalArgumentException
//     */
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
//        String uri = request.getRequestURI();
//        String method = request.getMethod();
//        AntPathMatcher matcher = new AntPathMatcher();
//
//        //先匹配路径，再匹配方法
//        //在白名单里面的请求直接返回null，即白名单内的请求不需要权限
//
//        //数据库里面查到的需要拦截的路径
//        boolean b1 = matcher.match("/admin",uri);
//
//        //数据里面查出来的当前路径对应的方法
////        boolean b2 = method.toUpperCase().equals("数据里面查出来的当前路径对应的方法");
////        if (b1 && b2){
//            return SecurityConfig.createList("ADMIN");
////        }
//
////        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return support;
//    }
//}

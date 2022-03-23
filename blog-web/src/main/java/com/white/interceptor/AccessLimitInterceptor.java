package com.white.interceptor;

import cn.hutool.json.JSONObject;
import com.white.annotation.AccessLimit;
import com.white.domain.LoggerInfo;
import com.white.domain.Result;
import com.white.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 控制接口访问频率
 *
 * @author fanfanli
 * @date 2021/8/11
 */

@Component
public class AccessLimitInterceptor implements HandlerInterceptor  {
    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            //方法上没有访问控制的注解，直接通过
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            String ip = request.getHeader("x-forwarded-for");
            ;
            String method = request.getMethod();



            String requestUri = request.getRequestURI();
            String redisKey = ip + ":" + method + ":" + requestUri;

//            LoggerInfo.initialization()
//                    .loggerName("com.white.interceptor.AccessLimitInterceptor")
//                    .messages("preHandle")
//                    .messages("redisKey", redisKey)
//                    .output();

            Integer count = null;
//            count = (Integer) redisCache.getCacheObject(redisKey);
            try {
                count = (Integer) redisCache.getCacheObject(redisKey);
            }catch (Exception e){
                //若不存在，则抛出异常，证明redis中没有redisKey对应的值
                e.printStackTrace();
            }

            if (count == null) {
                //在规定周期内第一次访问，存入redis
                redisCache.setCacheObject(redisKey, 1);
                redisCache.expire(redisKey, seconds);
            } else {
                System.out.println("count:"+count);
                if (count >= maxCount) {
                    //超出访问限制次数
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(String.valueOf(new JSONObject(Result.error().codeAndMessage("403", accessLimit.msg()))));
                    out.flush();
                    out.close();
                    return false;
                } else {
                    //没超出访问限制次数
                    redisCache.setCacheObject(redisKey, count + 1);
                }
            }
        }
        return true;
    }
}

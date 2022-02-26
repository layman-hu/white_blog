package com.white.handler.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.white.Result;
import com.white.ResultInfo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Logger logger = Logger.getLogger("com.white.handler.auth.MyAuthenticationFailureHandler");
        logger.info("MyAuthenticationFailureHandler:");
        logger.info("登录验证成功失败");
        logger.info("");

        ObjectMapper objectMapppr = new ObjectMapper();
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapppr.writeValueAsString(Result.error().codeAndMessage(ResultInfo.LOGIN_FAIL)));

    }
}

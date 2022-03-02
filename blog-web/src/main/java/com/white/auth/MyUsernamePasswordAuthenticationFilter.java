package com.white.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.white.LoggerInfo;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
        || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            if(!request.getMethod().equals("POST")){
                throw new AuthenticationServiceException(
                        "Authentication method not supported:\t" + request.getMethod());
            }
            Map<String,String> map = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();

            try(InputStream is = request.getInputStream()) {
                map = objectMapper.readValue(is,Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(map != null){
                String username = map.get("userName");
                String password = map.get("password");

                LoggerInfo.initialization()
                        .loggerName("com.white.auth.MyUsernamePasswordAuthenticationFilter")
                        .messages("MyUsernamePasswordAuthenticationFilter:")
                        .messages("userName:\t"+username)
                        .messages("password:\t"+password)
                        .output();

                if(username == null){
                    username = "";
                }
                if(password == null){
                    password = "";
                }

                username = username.trim();

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                this.setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
            return null;
        }
        return null;
    }
}


















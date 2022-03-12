package com.white.controller;

import com.white.domain.Result;
import com.white.api.LoginService;
import com.white.domain.ResultInfo;
import com.white.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        HashMap<String, String> map =  loginService.login(user);
        if(map.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.LOGIN_FAIL);
        }else {
            return Result.success()
                    .codeAndMessage(ResultInfo.LOGIN_SUCCESS)
                    .data("token",map.get("token"));
        }

    }

    @RequestMapping("/user/logout")
    public Result logout(){
        boolean logoutFlag = loginService.logout();
        if(logoutFlag){
            return Result.success().codeAndMessage("200","注销成功");
        }else {
            return Result.error().codeAndMessage("405","注销失败");
        }

    }
}

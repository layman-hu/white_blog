package com.white.controller;

import com.white.ResultInfo;
import com.white.api.TestService;
import com.white.Result;
import com.white.com.white.handler.MyRuntimeException;
import com.white.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;


    @GetMapping("/test")
    public Result test(){
//        String test = testService.test();
//        throw new MyRuntimeException("111","自定义异常");
////        if(StringUtils.hasLength(test)){
////            return Result.success().codeAndMessage(ResultInfo.SUCCESS).data("data",test);
////        }
////        else return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        User user = testService.getUserById(1);
        return Result.success().data("data",user);
    }
}

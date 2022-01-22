package com.white.controller;

import com.white.api.TestService;
import com.white.Result;
import com.white.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试模块")
public class TestController {
    @Autowired
    private TestService testService;


    @GetMapping("/test")
    @ApiOperation("测试接口")
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

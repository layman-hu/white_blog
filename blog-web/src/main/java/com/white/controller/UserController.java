package com.white.controller;


import com.white.domain.LoggerInfo;
import com.white.domain.Result;
import com.white.domain.ResultInfo;
import com.white.api.UserService;
import com.white.dto.UserDTO;
import com.white.vo.UserQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author white
 * @since 2022-02-01
 */
@RestController
@RequestMapping("/user")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserRolesByUserId")
    public Result getUserRolesByUserId(Integer id) {
        List<String> roleList = userService.getUserRolesByUserId(id);
        if(!roleList.isEmpty()){
//            LoggerInfo.initialization()
//                    .loggerName("com.white.controller.UserController")
//                    .messages("UserController:")
//                    .messages("roleList",roleList)
//                    .output();
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("roleList",roleList);
        }else {
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }
    }

    @GetMapping("/getUserList")
    @PreAuthorize("hasAnyAuthority('ADMIN','test')")
    public Result getUserList(UserQueryVO userQueryVO) {
        List<UserDTO> userList = userService.getUserByCondition(userQueryVO);
        if(!userList.isEmpty()){
            int userListSize = userList.size();

//            LoggerInfo.initialization()
//                    .loggerName("com.white.controller.UserController")
//                    .messages("UserController:")
//                    .messages("userList",userList)
//                    .messages("userListSize",userListSize)
//                    .output();


            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("userList",userList)
                    .data("userListSize",userListSize);
        }
        else {
//            LoggerInfo.initialization()
//                    .loggerName("com.white.controller.UserController")
//                    .messages("UserController:")
//                    .messages("userList",userList)
//                    .messages(ResultInfo.NOT_FOUND.toString())
//                    .output();
            return Result.error()
                    .codeAndMessage(ResultInfo.NOT_FOUND);
        }
    }
}


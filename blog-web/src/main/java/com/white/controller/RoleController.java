package com.white.controller;


import com.white.Result;
import com.white.ResultInfo;
import com.white.api.RoleService;
import com.white.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-02-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoleList")
    public Result getRoleList(){
        List<RoleDTO> roleDTOList = roleService.getRoleList();
        if (roleDTOList.size()>0){
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("roleList",roleDTOList);
        }else {
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }
    }
}


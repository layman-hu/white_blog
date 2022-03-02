package com.white.controller;


import com.white.Result;
import com.white.ResultInfo;
import com.white.api.MenuService;
import com.white.dto.MenuDTO;
import com.white.entity.Menu;
import com.white.entity.Role;
import com.white.mapper.MenuMapper;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author white
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("根据用户角色获取对应权限的菜单")
    @GetMapping("/getMenuListByUserRole")
    public Result getMenuListByUserRole(@RequestParam("roleName")String roleName){
        List<Menu> menuList = menuService.getAllMenuListByUserRole(roleName);
        if(!menuList.isEmpty()) {
            return Result.success().codeAndMessage(ResultInfo.SUCCESS).data("menuList",menuList);
        }else {
            return Result.error();
        }
    }
}


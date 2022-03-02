package com.white.controller;


import com.white.Result;
import com.white.ResultInfo;
import com.white.api.CategoryService;
import com.white.entity.Category;
import io.swagger.annotations.ApiOperation;
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
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取分类列表")
    @GetMapping("getCategoryList")
    public Result getCategoryList(){
        List<Category> categoryList = categoryService.list();
        if(!categoryList.isEmpty()){
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("categoryList",categoryList);
        }else {
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }

    }
}


package com.white.controller;


import com.white.domain.Result;
import com.white.domain.ResultInfo;
import com.white.api.TagService;
import com.white.entity.Tag;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取标签列表")
    @GetMapping("getTagList")
    public Result getTagList(){
        List<Tag> tagList = tagService.list();
        if(!tagList.isEmpty()){
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("tagList",tagList);
        }else {
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }

    }
}


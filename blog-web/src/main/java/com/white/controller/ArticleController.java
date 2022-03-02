package com.white.controller;


import com.white.Result;
import com.white.ResultInfo;
import com.white.api.ArticleService;
import com.white.vo.AddArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("上传或更新文章")
    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(@RequestBody AddArticleVO addArticleVO){

        int i = articleService.saveOrUpdateArticle(addArticleVO);
        if (i == -1){
            return Result.error().codeAndMessage("406","服务器无法根据客户端请求的内容特性完成请求\n");
        }else {
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }
    }

    @ApiOperation("分页查询文章列表")
    @GetMapping("/listArticle")
    public Result listArticle(
            @RequestParam(value = "currentPageNumber", defaultValue = "1",required = true)Integer currentPageNumber,
            @RequestParam(value = "pageSize", defaultValue = "7", required = true)Integer pageSize,
            @RequestParam(value = "queryTitleKeyword",required = false)String queryTitleKeyword){

    }
}


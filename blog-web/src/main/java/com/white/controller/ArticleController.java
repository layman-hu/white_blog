package com.white.controller;


import com.white.Result;
import com.white.ResultInfo;
import com.white.api.ArticleService;
import com.white.api.TagService;
import com.white.dto.ArticleListDTO;
import com.white.vo.AddArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

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

        int articleListSize = articleService.count();
        List<ArticleListDTO> articleListDTOList = articleService.listArticlePage(currentPageNumber, pageSize, queryTitleKeyword);

        //查询文章id对应的标签列表，并将其加入articleListDTOList内
        for(int i=0; i<articleListDTOList.size(); i++){
            Integer articleId = articleListDTOList.get(i).getArticleId();

            /**
             * ArticleListDTO的私有变量tagList必须初始化
             * 即
             * private List<String> tagList = new ArrayList<>();
             */
            articleListDTOList.get(i).getTagList().addAll(tagService.getTagListByArticleId(articleId));
        }

        if(!articleListDTOList.isEmpty()){
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleListDTOList)
                    .data("articleListSize",articleListSize);
        }else {
            return Result.error()
                    .codeAndMessage(ResultInfo.NOT_FOUND);
        }
    }

    @ApiOperation("根据文章ID，删除文章，同时删除与之关联的标签和分类")
    @GetMapping("/deleteArticleById")
    public Result deleteArticleById(@RequestParam("articleId")Integer articleId){
        int i = articleService.deleteArticleById(articleId);
        if (i == -1){
            return Result.error().codeAndMessage("406","服务器无法根据客户端请求的内容特性完成请求\n");
        }else {
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }
    }
}


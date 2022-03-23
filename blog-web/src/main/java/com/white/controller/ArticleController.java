package com.white.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.white.domain.Result;
import com.white.domain.ResultInfo;
import com.white.api.ArticleService;
import com.white.api.CategoryService;
import com.white.api.TagService;
import com.white.dto.ArticleDTO;
import com.white.dto.ArticleListDTO;
import com.white.entity.Article;
import com.white.entity.Category;
import com.white.vo.AddArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("上传或更新文章")
    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(@RequestBody AddArticleVO addArticleVO){

        int i = articleService.saveOrUpdateArticle(addArticleVO);
        if(i == -2){
            return Result.error().codeAndMessage("406","保存或更新文章失败\n");
        }else if (i == -1){
            return Result.error().codeAndMessage("406","文章标签关联表更新失败\n");
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

        //该数值用于前端列表分页展示
        int articleListSize = articleService.count();

        List<ArticleListDTO> articleListDTOList = articleService.listArticlePage(currentPageNumber, pageSize, queryTitleKeyword);

        //查询关键字不为空
        if (!"".equals(queryTitleKeyword)){
            //通过关键字查询后，列表不为空
            if(!articleListDTOList.isEmpty()){
                articleListSize = articleListDTOList.size();
            }else {
                //通过关键字查询后，列表为空
                articleListSize = 1;
            }
        }
        //查询文章id对应的标签列表，并将其加入articleListDTOList内
        for (ArticleListDTO articleListDTO : articleListDTOList) {
            Integer articleId = articleListDTO.getId();

            /**
             * ArticleListDTO的私有变量tagList必须初始化
             * 即
             * private List<String> tagList = new ArrayList<>();
             *
             * todo foreach遍历问题，能否改变列表元素的内容
             * 这里我不太理解，foreach遍历可以改变列表内容的吗，这和我以前学习的知识相冲突
             */
            articleListDTO.getTagIdList().addAll(tagService.getTagIdListByArticleId(articleId));
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

    @ApiOperation("根据文章ID，获取文章")
    @GetMapping("/getArticleById")
    public Result getArticleById(@RequestParam("articleId")Integer articleId){
        Article article = articleService.getById(articleId);

        if (article == null){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND)
                    .codeAndMessage("404","文章不存在");
        }else {

            ArticleDTO articleDTO = articleToArticleDTO(article);

            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("article",articleDTO);
        }
    }

    @ApiOperation("展示首页文章，参数为当前页，一页文章数定为5")
    @GetMapping("/homePageArticles")
    public Result homePageArticles(@RequestParam("currentPageNumber")Integer currentPageNumber, @RequestParam("pageSize")Integer pageSize){
        List<Article> articleList = articleService.homePageArticles(currentPageNumber,pageSize);

        //只计算公开的文章数量，隐藏的文章不在计数范围之内
        Integer allArticleNumber = articleService.count(new QueryWrapper<Article>().eq("STATUS",1));

        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            List<ArticleDTO> articleListResult = new ArrayList<>();
            for(Article article:articleList){
                ArticleDTO temp = articleToArticleDTO(article);
                articleListResult.add(temp);
            }

            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleListResult)
                    .data("currentPageNumber",currentPageNumber)
                    .data("total",allArticleNumber);

        }
    }

    @ApiOperation("根据分类名搜索文章，参数为当前页，一页文章数定为5")
    @GetMapping("/getArticlesByCategory")
    public Result getArticlesByCategory(@RequestParam(value = "currentPageNumber", defaultValue = "1",required = true)Integer currentPageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "5", required = true)Integer pageSize,
                                        @RequestParam(value = "categoryName",required = true)String categoryName){
        List<Article> articleList = articleService.getArticlesByCategory(currentPageNumber,pageSize,categoryName);

        Category category = categoryService.getOne( new QueryWrapper<Category>().eq("CATEGORY",categoryName));
        Integer allArticleNumber = articleService.count(new QueryWrapper<Article>().eq("STATUS",1).eq("CATEGORY_ID",category.getId()));



        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            List<ArticleDTO> articleListResult = new ArrayList<>();
            for(Article article:articleList){
                ArticleDTO temp = articleToArticleDTO(article,category);

                articleListResult.add(temp);
            }

            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleListResult)
                    .data("currentPageNumber",currentPageNumber)
                    .data("total",allArticleNumber);

        }
    }

    @ApiOperation("按最新创建时间查询文章列表，参数为当前页，一页文章数定为10")
    @GetMapping("/archives")
    public Result archives(@RequestParam(value = "currentPageNumber", defaultValue = "1",required = true)Integer currentPageNumber,
                           @RequestParam(value = "pageSize", defaultValue = "10", required = true)Integer pageSize){
        List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("STATUS",1).orderByDesc("CREATE_TIME"));
        Integer allArticleNumber = articleService.count(new QueryWrapper<Article>().eq("STATUS",1));
        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleList)
                    .data("total",allArticleNumber);
        }
    }

    @ApiOperation("关于作者的简介文章")
    @GetMapping("/about")
    public Result getAuthorArticle(){
        //这就没什么了，通过上帝视角，直接查简介文章的id
        Article article = articleService.getById(20);

        ArticleDTO temp = articleToArticleDTO(article);

        return Result.success().codeAndMessage(ResultInfo.SUCCESS).data("article",temp);
    }

    private ArticleDTO articleToArticleDTO(Article article){
        Integer articleId = article.getId();

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleId);
        articleDTO.setTitle(article.getTitle());
        articleDTO.setPicture(article.getPicture());
        articleDTO.setContent(article.getContent());
        articleDTO.setCategory(categoryService.getById(article.getCategoryId()));
        articleDTO.setCreateTime(article.getCreateTime());
        articleDTO.setUpdateTime(article.getUpdateTime());
        articleDTO.setIsTop(article.isTop());

        articleDTO.setDraft(article.isDraft());
        articleDTO.setWords(article.getWords());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setUserId(article.getUserId());
        articleDTO.setStatus(article.getStatus());

        //标签没找到则为空
        articleDTO.setTagList(tagService.getTagListByArticleId(articleId));

        return articleDTO;
    }

    private ArticleDTO articleToArticleDTO(Article article,Category category){
        Integer articleId = article.getId();

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleId);
        articleDTO.setTitle(article.getTitle());
        articleDTO.setPicture(article.getPicture());
        articleDTO.setContent(article.getContent());
        articleDTO.setCategory(category);
        articleDTO.setCreateTime(article.getCreateTime());
        articleDTO.setUpdateTime(article.getUpdateTime());
        articleDTO.setIsTop(article.isTop());

        articleDTO.setDraft(article.isDraft());
        articleDTO.setWords(article.getWords());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setUserId(article.getUserId());
        articleDTO.setStatus(article.getStatus());
        //标签没找到则为空
        articleDTO.setTagList(tagService.getTagListByArticleId(articleId));

        return articleDTO;
    }


}


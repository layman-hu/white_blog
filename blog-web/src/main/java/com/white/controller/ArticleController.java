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
        if (!queryTitleKeyword.equals("")){
            //通过关键字查询后，列表不为空
            if(!articleListDTOList.isEmpty()){
                articleListSize = articleListDTOList.size();
            }else {
                //通过关键字查询后，列表为空
                articleListSize = 1;
            }
        }
        //查询文章id对应的标签列表，并将其加入articleListDTOList内
        for(int i=0; i<articleListDTOList.size(); i++){
            Integer articleId = articleListDTOList.get(i).getArticleId();

            /**
             * ArticleListDTO的私有变量tagList必须初始化
             * 即
             * private List<String> tagList = new ArrayList<>();
             */
            articleListDTOList.get(i).getTagIdList().addAll(tagService.getTagIdListByArticleId(articleId));
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
    @GetMapping(value = {"/getArticleById","/{articleId}"})
    public Result getArticleById(@RequestParam("articleId")Integer articleId){
        Article article = articleService.getById(articleId);

//        List<Article> articleList = articleService.getArticleById(articleId);

        if (article == null){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND)
                    .codeAndMessage("404","文章不存在");
        }else {
            ArticleDTO articleDTO = new ArticleDTO();
                articleDTO.setArticleId(articleId);
                articleDTO.setArticleTitle(article.getTitle());
                articleDTO.setPicture(article.getPicture());
                articleDTO.setContent(article.getContent());
                articleDTO.setCategory(categoryService.getById(article.getCategoryId()));
                articleDTO.setCreateTime(article.getCreateTime());
                articleDTO.setUpdateTime(article.getUpdateTime());
                articleDTO.setIsTop(article.isTop());
                //标签没找到则为空
                articleDTO.setTagList(tagService.getTagListByArticleId(articleId));


            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("article",articleDTO);
        }
    }

    @ApiOperation("展示首页文章，参数为当前页，一页文章数定为5")
    @GetMapping("/homePageArticles")
    public Result homePageArticles(@RequestParam("currentPageNumber")Integer currentPageNumber, @RequestParam("pageSize")Integer pageSize){
        List<Article> articleList = articleService.homePageArticles(currentPageNumber,pageSize);
        Integer allArticleNumber = articleService.count();

        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            List<ArticleDTO> articleDTOS = new ArrayList<>();
            for(Article article:articleList){
                Integer articleId = article.getId();

                ArticleDTO temp = new ArticleDTO();
                temp.setArticleId(articleId);
                temp.setArticleTitle(article.getTitle());
                temp.setPicture(article.getPicture());
                temp.setContent(article.getContent());
                temp.setCategory(categoryService.getById(article.getCategoryId()));
                temp.setCreateTime(article.getCreateTime());
                temp.setUpdateTime(article.getUpdateTime());
                temp.setIsTop(article.isTop());
                temp.setTagList(tagService.getTagListByArticleId(articleId));

                articleDTOS.add(temp);
            }

            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleDTOS)
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
        System.out.println("category");
        System.out.println(category);

        QueryWrapper<Article> queryWrapper = new QueryWrapper();
        Integer allArticleNumber = articleService.count(queryWrapper.eq("CATEGORY_ID",category.getId()));
        System.out.println("allArticleNumber");
        System.out.println(allArticleNumber);


        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            List<ArticleDTO> articleDTOS = new ArrayList<>();
            for(Article article:articleList){
                Integer articleId = article.getId();

                ArticleDTO temp = new ArticleDTO();
                temp.setArticleId(articleId);
                temp.setArticleTitle(article.getTitle());
                temp.setPicture(article.getPicture());
                temp.setContent(article.getContent());
                temp.setCategory(category);
                temp.setCreateTime(article.getCreateTime());
                temp.setUpdateTime(article.getUpdateTime());
                temp.setIsTop(article.isTop());
                temp.setTagList(tagService.getTagListByArticleId(articleId));

                articleDTOS.add(temp);
            }

            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleDTOS)
                    .data("currentPageNumber",currentPageNumber)
                    .data("total",allArticleNumber);

        }
    }

    @ApiOperation("按最新创建时间查询文章列表，参数为当前页，一页文章数定为10")
    @GetMapping("/archives")
    public Result archives(@RequestParam(value = "currentPageNumber", defaultValue = "1",required = true)Integer currentPageNumber,
                           @RequestParam(value = "pageSize", defaultValue = "10", required = true)Integer pageSize){
        List<Article> articleList = articleService.list(new QueryWrapper<Article>().orderByDesc("CREATE_TIME"));
        Integer allArticleNumber = articleService.count();
        if(articleList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("articleList",articleList)
                    .data("total",allArticleNumber);
        }
    }

    @ApiOperation("点击封面进入文章，前端根据文章id查询文章")
    @GetMapping("/articleList/{articleId}")
    public Result getArticleAndTagsByArticleId(@PathVariable("articleId")Integer articleId){
        Article article = articleService.getById(articleId);
        if(article == null){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            return Result.success()
                    .codeAndMessage(ResultInfo.SUCCESS)
                    .data("article",article)
                    .data("tagIdList",tagService.getTagIdListByArticleId(articleId));
        }

    }

}


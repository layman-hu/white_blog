package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.dto.ArticleDTO;
import com.white.dto.ArticleListDTO;
import com.white.entity.Article;
import com.white.vo.AddArticleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
public interface ArticleService extends IService<Article> {
    /**
     * 新增或更新文章
     * @param addArticleVO 前端返回的VO实体类
     * @return 成功码，整数型
     */
    Integer saveOrUpdateArticle(AddArticleVO addArticleVO);

    /**
     * 分页获取文章列表
     * @param currentPageNumber 前端返回的当前页码
     * @param pageSize 前端返回的页面大小
     * @param articleTitle 前端返回的查询关键字
     * @return 后端返回的文章列表DTO实体类
     */
    List<ArticleListDTO> listArticlePage(Integer currentPageNumber, Integer pageSize, String articleTitle);

    /**
     * 根据文章ID，删除文章，同时删除与之关联的标签和分类
     * @param articleId 文章id
     * @return 数据库中影响行数，整数型
     */
    Integer deleteArticleById(Integer articleId);

    /**
     * 博客首页展示，展示文章
     * @param currentPageNumber 前端返回的当前页码
     * @param pageSize 前端返回的页面大小
     * @return 文章列表
     */
    List<Article> homePageArticles(Integer currentPageNumber,Integer pageSize);

    /**
     *按分类查找文章
     * @param currentPageNumber 前端返回的当前页码
     * @param pageSize 前端返回的页面大小
     * @param categoryName 分类名
     * @return 文章列表
     */
    List<Article> getArticlesByCategory(Integer currentPageNumber, Integer pageSize, String categoryName);


}

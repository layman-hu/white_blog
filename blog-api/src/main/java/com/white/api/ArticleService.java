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
     * @param addArticleVO
     * @return
     */
    Integer saveOrUpdateArticle(AddArticleVO addArticleVO);

    /**
     * 分页获取文章列表
     * @param currentPageNumber
     * @param pageSize
     * @param articleTitle
     * @return
     */
    List<ArticleListDTO> listArticlePage(Integer currentPageNumber, Integer pageSize, String articleTitle);

    /**
     * 根据文章ID，删除文章，同时删除与之关联的标签和分类
     * @param articleId
     * @return
     */
    Integer deleteArticleById(Integer articleId);

    List<Article> homePageArticles(Integer currentPageNumber,Integer pageSize);

    List<Article> getArticlesByCategory(Integer currentPageNumber, Integer pageSize, String categoryName);

//    /**
//     * 根据文章ID，获取文章
//     * @param articleId
//     * @return
//     */
//    List<Article> getArticleById(Integer articleId);
}

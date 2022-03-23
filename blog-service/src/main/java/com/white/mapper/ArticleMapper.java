package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.dto.ArticleDTO;
import com.white.dto.ArticleListDTO;
import com.white.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     *更新文章的标签列表
     * @param articleId 文章id
     * @param tagIdList 标签id列表
     * @return 数据库影响行数
     */
    Integer saveOrUpdateConArticleTag(@Param("articleId") Integer articleId, @Param("tagIdList") List<Integer> tagIdList);

    /**
     * 分页获取文章列表
     * @param sqlQueryNumber 数据库所需的查询位置
     * @param pageSize 前端返回的页面大小
     * @param articleTitle 前端返回的查询关键字
     * @return 后端返回的文章列表DTO实体类
     */
    List<ArticleListDTO> listArticlePage(@Param("sqlQueryNumber")  Integer sqlQueryNumber, @Param("pageSize")  Integer pageSize,@Param("articleTitle")  String articleTitle);

    /**
     * 根据文章ID，删除文章，同时删除与之关联的标签和分类
     * @param articleId 文章id
     * @return 数据库中影响行数，整数型
     */
    Integer deleteArticleById(@Param("articleId") Integer articleId);

    /**
     * 博客首页展示，展示文章
     * @param sqlQueryNumber 数据库所需的查询位置
     * @param pageSize 前端返回的页面大小
     * @return 文章列表
     */
    List<Article> homePageArticles(@Param("sqlQueryNumber")  Integer sqlQueryNumber, @Param("pageSize")  Integer pageSize);

    /**
     * 查询对应文章id，是否存在标签
     * @param articleId 文章id
     * @return 包含文章id的标签个数
     */
    Integer isExistArticleId(@Param("articleId") Integer articleId);

    /**
     * 通过文章id更新标签列表
     * @param articleId 文章id
     * @param tagIdList 标签id列表
     * @return 数据库中影响行数，整数型
     */
    Integer updateConArticleTag(@Param("articleId") Integer articleId, @Param("tagIdList") List<Integer> tagIdList);

    /**
     * 通过文章id插入标签列表
     * @param articleId 文章id
     * @param tagIdList 标签id列表
     * @return 数据库中影响行数，整数型
     */
    Integer insertConArticleTag(@Param("articleId") Integer articleId, @Param("tagIdList") List<Integer> tagIdList);

    /**
     *按分类查找文章
     * @param sqlQueryNumber 数据库所需的查询位置
     * @param pageSize 前端返回的页面大小
     * @param categoryName 分类名
     * @return 文章列表
     */
    List<Article> getArticlesByCategory(@Param("sqlQueryNumber")  Integer sqlQueryNumber, @Param("pageSize")   Integer pageSize, @Param("categoryName")   String categoryName);

}

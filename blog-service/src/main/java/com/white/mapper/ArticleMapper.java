package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    Integer saveOrUpdateConArticleTag(@Param("articleId") Integer articleId, @Param("tagIdList") List<Integer> tagIdList);

    List<ArticleListDTO> listArticlePage(@Param("sqlQueryNumber")  Integer sqlQueryNumber, @Param("pageSize")  Integer pageSize,@Param("articleTitle")  String articleTitle);

    Integer deleteArticleById(@Param("articleId") Integer articleId);
}

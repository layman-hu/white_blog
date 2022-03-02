package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.Article;
import com.white.vo.AddArticleVO;

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
}

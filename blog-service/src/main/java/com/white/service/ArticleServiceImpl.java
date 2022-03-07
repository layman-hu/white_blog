package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.LoggerInfo;
import com.white.api.ArticleService;
import com.white.dto.ArticleDTO;
import com.white.dto.ArticleListDTO;
import com.white.entity.Article;
import com.white.mapper.ArticleMapper;
import com.white.vo.AddArticleVO;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    //涉及到增删改查，有事务问题
    @Override
    public Integer saveOrUpdateArticle(AddArticleVO addArticleVO) {

        //得到前端传递过来的数据
        //新增或更新文章
        Integer id = addArticleVO.getId();

        Article article = new Article();
                article.setId(addArticleVO.getId());
                article.setTitle(addArticleVO.getTitle());
                article.setPicture(addArticleVO.getPicture());
                article.setCategoryId(addArticleVO.getCategoryId());
                article.setContent(addArticleVO.getContent());
                article.setTop(addArticleVO.isTop());
                article.setDraft(addArticleVO.isDraft());

        Date date = new Date();
        if(null==id){
            //表示新增文章
            article.setCreateTime(date);
            article.setUpdateTime(date);
        }else {
            article.setUpdateTime(date);
        }
        //保存或更新文章
        boolean saveOrUpdateFlag = this.saveOrUpdate(article);
        if (!saveOrUpdateFlag){
            //保存或更新文章失败
            return -2;
        }
        //在设置关联表
        //获取标签的id
        List<Integer> tagIdList = addArticleVO.getTagIdList();
        LoggerInfo.initialization()
                .loggerName("com.white.service.ArticleServiceImpl")
                .messages("ArticleServiceImpl:")
//                .messages("addArticleVO.getTagIdList():",addArticleVO.getTagIdList())
                .messages("tagIdList:",tagIdList)
                .output();

        //获取文章主键id
        Integer articleId = article.getId();
        //新增或者更新关联表
        //判断con_article_tag中，是否存在articleId
        int count = this.baseMapper.isExistArticleId(articleId);

        //关联表更新失败
        int i = -1;
        if(count > 0){
            //关联表中存在articleId，更新关联表
            i = this.baseMapper.updateConArticleTag(articleId,tagIdList);
        }else if(count == 0){
            i = this.baseMapper.insertConArticleTag(articleId,tagIdList);
        }

        return i;
    }

    @Override
    public List<ArticleListDTO> listArticlePage(Integer currentPageNumber, Integer pageSize, String articleTitle) {
        Integer sqlQueryNumber = (currentPageNumber-1)*pageSize;
        return this.baseMapper.listArticlePage(sqlQueryNumber,pageSize,articleTitle);
    }

    @Override
    public Integer deleteArticleById(Integer articleId) {
        return this.baseMapper.deleteArticleById(articleId);
    }

    @Override
    public List<ArticleDTO> homePageArticles(Integer currentPageNumber) {
        //初识查询点为0
        Integer sqlQueryNumber = (currentPageNumber-1)*10;

        return this.baseMapper.homePageArticles(sqlQueryNumber);
    }

//    @Override
//    public List<Article> getArticleById(Integer articleId) {
//        return this.baseMapper.getArticleById(articleId);
//    }
}

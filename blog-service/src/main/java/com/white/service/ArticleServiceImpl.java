package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.ArticleService;
import com.white.dto.ArticleListDTO;
import com.white.entity.Article;
import com.white.mapper.ArticleMapper;
import com.white.vo.AddArticleVO;
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
        Article article = new Article(addArticleVO);
        Date date = new Date();
        if(null==id){
            //表示新增文章

            article.setCreateTime(date);
        }else {
            article.setUpdateTime(date);
        }
        //保存或更新文章
        boolean saveOrUpdateFlag = this.saveOrUpdate(article);
        if (!saveOrUpdateFlag){
            return -1;
        }
        //在设置关联表
        //获取标签的id
        List<Integer> tagIdList = addArticleVO.getTagIdList();
        //获取文章主键id
        Integer articleId = article.getId();
        //新增或者更新关联表
        int i = this.baseMapper.saveOrUpdateConArticleTag(articleId,tagIdList);
        if (i<0){
            return -1;
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
}

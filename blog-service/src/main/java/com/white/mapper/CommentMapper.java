package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-03-14
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据文章id，查询对应的评论列表
     * @param sqlQueryNumber 数据库所需的查询位置
     * @param pageSize 前端返回的页面大小
     * @param articleId 文章id
     * @return 评论列表
     */
    List<Comment> getCommentListByArticleId(@Param("sqlQueryNumber")  Integer sqlQueryNumber, @Param("pageSize")  Integer pageSize, @Param("articleId") Integer articleId);
}

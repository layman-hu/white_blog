package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.dto.CommentDTO;
import com.white.entity.Comment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-03-14
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据文章id，查询对应的评论列表
     * @param currentPageNumber 前端返回的当前页码
     * @param pageSize 前端返回的页面大小
     * @param articleId 文章id
     * @return 评论列表
     */
    List<Comment> getCommentListByArticleId(Integer currentPageNumber, Integer pageSize, Integer articleId);
}

package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.CommentService;
import com.white.dto.CommentDTO;
import com.white.entity.Comment;
import com.white.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-03-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<Comment> getCommentListByArticleId(Integer currentPageNumber, Integer pageSize, Integer articleId) {
        Integer sqlQueryNumber = (currentPageNumber - 1) * pageSize;
        Comment topComment = null;
        try {
            List<Comment> commentList = this.baseMapper.getCommentListByArticleId(sqlQueryNumber, pageSize, articleId);

            //设置一个虚拟的顶级父评论
            topComment = new Comment();

            //设置一个标志数组  未访问标false，访问标true
            boolean[] isVisited = new boolean[commentList.size()];
            Arrays.fill(isVisited, Boolean.FALSE);

            //遍历删除父类菜单，将其放入menuParentList
            for (int i = 0; i < commentList.size(); i++) {
                Comment temp = commentList.get(i);
                if (temp.getParentCommentId() == null || temp.getParentCommentId() <= 0) {
                    topComment.getChildren().add(temp);
                    isVisited[i] = true;
                    //查找子评论
                    findChildren(temp, commentList, isVisited);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topComment.getChildren();
    }

    private void findChildren(Comment parentComment, List<Comment> childrenComment, boolean []isVisited){

        //1.判断父评论本身，是否包含子评论
        for(int i=0; i<childrenComment.size(); i++){
            //isVisited[i]为false，尚未访问
            if(!isVisited[i]){
                Comment temp = childrenComment.get(i);
                if(parentComment.getId().equals(temp.getParentCommentId())){
                    parentComment.getChildren().add(temp);
                    isVisited[i] = true;
                }
            }
        }

        //2.如果父评论没有子评论，直接返回
        if(parentComment.getChildren() == null || parentComment.getChildren().size() <= 0){
            return;
        }

        //3.判断子列表中是否还包含子列表
        List<Comment> list = parentComment.getChildren();
        for(int i=0; i<list.size(); i++){
            if(!isVisited[i]){
                Comment temp = childrenComment.get(i);
                if(list.get(i).getId().equals(temp.getParentCommentId())){
                    findChildren(list.get(i),childrenComment,isVisited);
                }
            }

        }
    }
}

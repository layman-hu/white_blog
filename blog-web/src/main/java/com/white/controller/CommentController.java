package com.white.controller;


import cn.hutool.core.bean.BeanUtil;
import com.white.annotation.AccessLimit;
import com.white.api.CommentService;
import com.white.domain.Result;
import com.white.domain.ResultInfo;
import com.white.dto.CommentDTO;
import com.white.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author white
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 分页查询某个博客下的根评论
     */

    @GetMapping("/getCommentListByArticleId")
    public Result getCommentListByArticleId(@RequestParam(value = "currentPageNumber", defaultValue = "1",required = false)Integer currentPageNumber,
                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false)Integer pageSize,
                                            @RequestParam(value = "articleId", required = true)Integer articleId){
        List<Comment> commentList = commentService.getCommentListByArticleId(currentPageNumber,pageSize,articleId);
        if(commentList.isEmpty()){
            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
        }else {
            return Result.success().codeAndMessage(ResultInfo.SUCCESS)
                    .data("commentList",commentList);
        }
    }

    @AccessLimit(seconds = 30, maxCount = 1, msg = "30秒内只能提交一次评论")
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment){
        //常用xss语句注入检测
        List<String> illegalStrs = new ArrayList<>();
        illegalStrs.add("<script>");
        illegalStrs.add("script");
        illegalStrs.add("alert");
        illegalStrs.add("javascript");
        illegalStrs.add("src=");
        for(String s:illegalStrs){
            boolean isContentContain = comment.getContent().contains(s);
            boolean isNicknameContain = comment.getNickname().contains(s);
            boolean isEmainContain = comment.getEmail().contains(s);
            boolean isWebsiteContain = comment.getWebsite().contains(s);

            if(isContentContain || isNicknameContain || isEmainContain || isWebsiteContain){
                return Result.error().codeAndMessage("410","非法输入");
            }
        }
        Comment commentResult = new Comment();
        BeanUtil.copyProperties(comment,commentResult,"id","ip","createTime","children");
        commentResult.setCreateTime(new Date());
        commentResult.setIp("");


        boolean flag = commentService.saveOrUpdate(commentResult);
        if(flag){
            return Result.success().codeAndMessage(ResultInfo.SUCCESS);
        }else {
            return Result.error();
        }

    }

}


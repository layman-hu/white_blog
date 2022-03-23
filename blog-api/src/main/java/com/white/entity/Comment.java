package com.white.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author white
 * @since 2022-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_comment")
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "游客（或作者）评论昵称")
    @TableField("NICKNAME")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "评论内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "游客头像")
    @TableField("AVATAR")
    private String avatar;

    @ApiModelProperty(value = "评论时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "评论者ip地址")
    @TableField("IP")
    private String ip;

    @ApiModelProperty(value = "是否是作者评论 0不是作者评论 1是作者评论")
    @TableField("IS_AUTHOR_COMMENT")
    private Boolean isAuthorComment;

    @ApiModelProperty(value = "评论是否公开 0隐藏1公开")
    @TableField("IS_PUBLISHED")
    private Boolean isPublished;

    @ApiModelProperty(value = "评论所属的文章id")
    @TableField("ARTICLE_ID")
    private Integer articleId;

    @ApiModelProperty(value = "父评论昵称")
    @TableField("PARENT_COMMENT_NICKNAME")
    private String parentCommentNickname;

    @ApiModelProperty(value = "父评论id，-1为根评论")
    @TableField("PARENT_COMMENT_ID")
    private Integer parentCommentId;

    @ApiModelProperty(value = "游客的个人网站")
    @TableField("WEBSITE")
    private String website;

    @ApiModelProperty(value = "QQ若为真，自动获取QQ头像和昵称。")
    @TableField("QQ")
    private String qq;

    @TableField(exist = false)
    private List<Comment> children = new ArrayList<>();
}

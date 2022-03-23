package com.white.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AddArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面贴图，链接地址
     */
    private String picture;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 标签ID列表
     */
    private List<Integer> tagIdList;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否置顶 0不置顶 1置顶
     */
    private boolean isTop;

    /**
     * 是否存草稿 0不存草稿 1存草稿
     */
    private boolean isDraft;

    /**
     * 文章字数
     */
    private Integer words;

    /**
     * 文章摘要，文章前100字
     */
    private String description;

    /**
     * 文章作者id
     */
    private Integer userId;

    /**
     * 文章状态 0隐藏 1公开
     */
    private Integer status;
}

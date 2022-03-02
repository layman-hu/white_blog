package com.white.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.white.vo.AddArticleVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_article")
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "封面贴图，链接地址")
    @TableField("PICTURE")
    private String picture;

    @ApiModelProperty(value = "分类ID")
    @TableField("CATEGORY_ID")
    private Integer categoryId;

    @ApiModelProperty(value = "内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "是否置顶 0不置顶 1置顶")
    @TableField("IS_TOP")
    private boolean isTop;

    @ApiModelProperty(value = "是否存草稿 0不存草稿 1存草稿")
    @TableField("IS_DRAFT")
    private boolean isDraft;

    public Article(AddArticleVO addArticleVO) {
        this.id = addArticleVO.getId();
        this.title = addArticleVO.getTitle();
        this.picture = addArticleVO.getPicture();
        this.categoryId = addArticleVO.getCategoryId();
        this.content = addArticleVO.getContent();
        this.isTop = addArticleVO.isTop();
        this.isDraft = addArticleVO.isDraft();
    }
}

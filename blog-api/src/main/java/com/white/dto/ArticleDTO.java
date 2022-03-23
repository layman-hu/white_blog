package com.white.dto;

import com.white.entity.Category;
import com.white.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 传给前端首页，用于首页文章展示
 */
@Data
public class ArticleDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String picture;
    private String content;
    private Category category;

    private Date createTime;
    private Date updateTime;
    private Boolean isTop;

    private boolean isDraft;
    private Integer words;
    private String description;
    private Integer userId;
    private Integer status;

    private List<Tag> tagList;
}

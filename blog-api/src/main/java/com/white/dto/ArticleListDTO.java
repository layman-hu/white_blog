package com.white.dto;

import com.white.entity.Category;
import com.white.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class ArticleListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer articleId;
    private String articleTitle;
    private Integer categoryId;

    private Date createTime;
    private Date updateTime;
    private Boolean isTop;

    private List<Integer> tagIdList = new ArrayList<>();
}

package com.white.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {

    private Integer id;
    private String avatar;
    private String website;
    private String nickname;
    private Date createTime;
    private Boolean isAuthorComment;
    private String content;

    private Integer parentId;
    private List<CommentDTO> children;


}

package com.white.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //角色名
    private String roleName;
    //昵称
    private String nickName;
    //当前页码数
    private Integer currentPageNumber;
    //查询表每一页的大小
    private Integer pageSize;
}

package com.white.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 前端返回的用户角色
     */
    private String roleName;

    /**
     * 前端返回的用户昵称
     */
    private String nickName;

    /**
     * 前端返回的当前页码数
     */
    private Integer currentPageNumber;

    /**
     * 后端Mysql所需的sql语句查询位置
     */
    private Integer sqlQueryNumber;

    /**
     * 后端Mysql所需的查询表每一页的大小
     */
    private Integer pageSize;
}

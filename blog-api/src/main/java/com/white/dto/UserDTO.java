package com.white.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class UserDTO {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 头像，地址
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 1禁言 0正常发言
     */
    private Boolean isSilence;
}

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
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("NICKNAME")
    private String nickname;

    @ApiModelProperty(value = "头像，地址")
    @TableField("AVATAR")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "个人简介")
    @TableField("INTRODUCE")
    private String introduce;


}

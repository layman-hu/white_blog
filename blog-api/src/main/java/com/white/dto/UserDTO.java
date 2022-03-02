package com.white.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    @ApiModelProperty(value = "头像，地址")
    @TableField("AVATAR")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    @TableField("NICK_NAME")
    private String nickName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "1禁言 0正常发言")
    @TableField("IS_SILENCE")
    private Boolean isSilence;
}

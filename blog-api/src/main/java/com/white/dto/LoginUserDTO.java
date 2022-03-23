package com.white.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Administrator
 * 返回后端登陆界面时，需要的user实体类
 * 该实体类会存储于前端缓存中
 */
@Data
@AllArgsConstructor
public class LoginUserDTO {
    private Integer id;
    private String username;
    private String avatar;
}

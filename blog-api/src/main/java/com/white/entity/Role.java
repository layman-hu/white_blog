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
@TableName("tb_role")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;


}

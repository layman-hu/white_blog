package com.white.mapper;

import com.white.dto.RoleDTO;
import com.white.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleDTO> getRoleList();
}

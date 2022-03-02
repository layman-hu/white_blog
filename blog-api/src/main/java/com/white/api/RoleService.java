package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.dto.RoleDTO;
import com.white.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
public interface RoleService extends IService<Role> {
    List<RoleDTO> getRoleList();
}

package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.RoleService;
import com.white.dto.RoleDTO;
import com.white.entity.Role;
import com.white.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<RoleDTO> getRoleList() {
        return this.baseMapper.getRoleList();
    }
}

package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.Role;
import com.white.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

}

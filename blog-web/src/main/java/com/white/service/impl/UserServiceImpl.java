package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.mapper.UserMapper;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {

}

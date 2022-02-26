package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户ID获取用户角色
     * @return
     */
    List<String> getUserRolesByUserId(Integer id);
}

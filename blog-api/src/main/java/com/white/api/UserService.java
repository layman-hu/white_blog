package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.dto.UserDTO;
import com.white.entity.User;
import com.white.vo.UserQueryVO;

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
     * @param id 用户id
     * @return 字符串列表
     */
    List<String> getUserRolesByUserId(Integer id);

    /**
     * 条件查询用户信息
     * @param userQueryVO 前端返回的Vo实体类
     * @return 后端返回的DTO实体类
     */
    List<UserDTO> getUserByCondition(UserQueryVO userQueryVO);
}

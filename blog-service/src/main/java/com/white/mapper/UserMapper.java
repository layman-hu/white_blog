package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.dto.UserDTO;
import com.white.entity.User;
import com.white.vo.UserQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户ID获取用户角色
     * @return
     */
    List<String> getUserRolesByUserId(@Param("id") Integer id);

    /**
     * 条件查询用户信息
     * @return
     */
    List<UserDTO> getUserByCondition(@Param("userQueryVO") UserQueryVO userQueryVO);
}

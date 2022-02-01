package com.white.mapper;

import com.white.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-02-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

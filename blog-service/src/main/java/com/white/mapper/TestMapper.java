package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper extends BaseMapper<User> {
    User getUserById(@Param("id") Integer id);
}

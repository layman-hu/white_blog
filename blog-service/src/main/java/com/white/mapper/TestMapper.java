package com.white.mapper;

import com.white.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {
    User getUserById(@Param("id") Integer id);
}

package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-02-27
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过角色名，返回对应权限的所有菜单目录（目录未排序整理）
     * @param roleName
     * @return
     */
    List<Menu> getAllMenuList(@Param("roleName") String roleName);
}

package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-02-27
 */
public interface MenuService extends IService<Menu> {

    /**
     * 通过角色名，返回对应权限的所有菜单目录（目录未排序整理）
     * @param roleName
     * @return
     */
    List<Menu> getAllMenuListByUserRole(String roleName);
}

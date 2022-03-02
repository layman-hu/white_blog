package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.LoggerInfo;
import com.white.api.MenuService;
import com.white.entity.Menu;
import com.white.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-02-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> getAllMenuListByUserRole(String roleName) {
        //后台管理菜单，最多两级
        LoggerInfo.initialization()
                .loggerName("com.white.service.MenuServiceImpl")
                .messages("MenuServiceImpl:")
                .messages("后台管理菜单，递归查询")
                .output();

        //顶级父类菜单
        List<Menu> menuParentList = new ArrayList<>();
        try {
            //通过角色名，返回对应权限的所有菜单目录（目录未排序整理）
            List<Menu> menuList = this.baseMapper.getAllMenuList(roleName);

            //遍历删除顶级父类菜单，将其放入menuParentList
            Iterator<Menu> iterator = menuList.iterator();
            while (iterator.hasNext()){
                Menu tempMenu = iterator.next();
                if(tempMenu.getParentId() == null || tempMenu.getParentId() == 0){
                    menuParentList.add(tempMenu);
                    iterator.remove();
                    findChildren(tempMenu,menuList);
                }
            }

//            //对菜单目录进行排序整理，返回前端需要的菜单格式
//            Iterator<Menu> iteratorParent = menuParentList.iterator();
//            Iterator<Menu> iteratorChildren = null;
//            while (iteratorParent.hasNext()){
//                Menu parentMenu = iteratorParent.next();
//
//                iteratorChildren = menuList.iterator();
//                while (iteratorChildren.hasNext()){
//                    Menu childrenMenu = iteratorChildren.next();
//                    if(parentMenu.getId().equals(childrenMenu.getParentId())){
//                        parentMenu.getChildren().add(childrenMenu);
//                        iteratorChildren.remove();
//                    }
//                }
//            }

        }catch (Exception e){
            throw e;
        }
        return menuParentList;
    }

    //查找并链接子菜单
    private void findChildren(Menu parentMenu, List<Menu> childrenMenuList){
        Iterator<Menu> it = null;
        if(childrenMenuList.size()>0){
            it = childrenMenuList.iterator();
        }else {
            return;
        }

        while (it.hasNext()){
            Menu childrenMenu = it.next();
            //父菜单的id  与 子菜单中的父菜单id 相同，进行链接
            if(parentMenu.getId().equals(childrenMenu.getParentId())){
                parentMenu.getChildren().add(childrenMenu);
                it.remove();
            }
        }

        if(parentMenu.getChildren().size() <= 0){
            //父菜单不包含子菜单（下一级菜单）
            return;
        }else{
            //父菜单包含子菜单，再对子菜单进行 查找并链接子菜单
            for(Menu parentMenu2:parentMenu.getChildren()){
                findChildren(parentMenu2,childrenMenuList);
            }
        }

    }
}

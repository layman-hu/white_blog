package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        //顶级父类菜单
        List<Menu> menuParentList = new ArrayList<>();
        try {
            //通过角色名，返回对应权限的所有菜单目录（目录未排序整理）
            List<Menu> menuList = this.baseMapper.getAllMenuList(roleName);


            //遍历删除顶级父类菜单，将其放入menuParentList
            Iterator<Menu> iterator = menuList.iterator();
            while (iterator.hasNext()){
                if(iterator.next().getParentId() == null || iterator.next().getParentId() == 0){
                    menuParentList.add(iterator.next());
                    iterator.remove();
                }
            }

            for (Menu parent:menuParentList){
                iterator = menuList.iterator();
                while (iterator.hasNext()){
                    if(parent.getId().equals(iterator.next().getParentId())){
                        parent.getChildren().add(iterator.next());
                        iterator.remove();
                    }
                }
            }



            //对菜单目录进行排序整理，返回前端需要的菜单格式

            for(Menu menu:menuList){


                if(menu.getParentId() == null || menu.getParentId() == 0){

                }else {
                    for (Menu parent:menuList){
                        if(menu.getParentId().equals(parent.getId())){
//                            menuDTO.getChildren().add(new MenuDTO().isChildren(menu));
                            //有bug
                        }
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        return menuParentList;
    }
}

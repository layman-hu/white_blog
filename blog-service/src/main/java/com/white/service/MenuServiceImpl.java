package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.RoleService;
import com.white.api.MenuService;
import com.white.entity.Menu;
import com.white.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private RoleService roleService;

    @Override
    public List<Menu> getAllMenuListByUserRole(String roleName) {

        //虚拟一个顶级父类菜单
        Menu menuParent = new Menu();

         //顶级父类菜单
//        List<Menu> menuParentList = new ArrayList<>();
        try {
            //通过角色名，返回对应权限的所有菜单目录（目录未排序整理）
            List<Menu> menuList = this.baseMapper.getAllMenuList(roleName);


            //未访问标false，访问标true
            boolean []isMenuVisited = new boolean[menuList.size()];
            Arrays.fill(isMenuVisited,Boolean.FALSE);

            //遍历删除顶级父类菜单，将其放入menuParentList
            for(int i=0; i<menuList.size(); i++){
                //若菜单没有父菜单id，说明它是实际的顶级菜单
                Menu temp = menuList.get(i);
                if(temp.getParentId() == null || temp.getParentId() == 0){
                    isMenuVisited[i] = true;
                    menuParent.getChildren().add(temp);
                    findChildren(temp,menuList,isMenuVisited);
                }
            }

//            Iterator<Menu> iterator = menuList.iterator();
//            while (iterator.hasNext()){
//                Menu tempMenu = iterator.next();
//                if(tempMenu.getParentId() == null || tempMenu.getParentId() == 0){
//                    menuParentList.add(tempMenu);
//                    iterator.remove();
//                    findChildren(tempMenu,menuList);
//                }
//            }


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
        return menuParent.getChildren();
    }

    @Override
    public List<Menu> getMenuListByUserId(String userId) {
        //todo 目前一个用户对应一个角色，之后可以拓展，或者优化结构
        String roleName = roleService.getRoleNameByUserId(userId);

        return getAllMenuListByUserRole(roleName);
    }

    //查找并链接子菜单
    private void findChildren(Menu parentMenu, List<Menu> menuList, boolean[] isMenuVisited){
        //1.判断父菜单本身     是否存在子菜单
        for (int i=0; i<isMenuVisited.length; i++){
            if(!isMenuVisited[i]){
                Menu temp = menuList.get(i);
                if(parentMenu.getId().equals(temp.getParentId())){
                    isMenuVisited[i] = true;
                    parentMenu.getChildren().add(temp);
                }
            }
        }

        //如果父菜单不存在子菜单，直接返回
        if(parentMenu.getChildren().size() <= 0 || parentMenu.getChildren() == null) {
            return;
        }

        //2.判断子菜单列表中每个子菜单       是否存在子菜单
        List<Menu> childrenMenuList = parentMenu.getChildren();
        for(int i=0; i<childrenMenuList.size(); i++){
            if(!isMenuVisited[i]){
                findChildren(childrenMenuList.get(i),menuList,isMenuVisited);
            }
        }

    }
}

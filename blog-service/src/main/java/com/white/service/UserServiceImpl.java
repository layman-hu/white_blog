package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.LoggerInfo;
import com.white.api.UserService;
import com.white.dto.UserDTO;
import com.white.entity.User;
import com.white.mapper.UserMapper;
import com.white.vo.UserQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<String> getUserRolesByUserId(Integer id) {
        return this.baseMapper.getUserRolesByUserId(id);
//        return userMapper.getUserRolesByUserId(id);
    }

    @Override
    public List<UserDTO> getUserByCondition(UserQueryVO userQueryVO) {
        //前端传回的数据是用户列表的当前页码
        //而后端查询需要的是startIndex，即从数据库列表中的第几个开始，初始值为0
        //将前端数据转换成后端数据
        LoggerInfo.initialization()
                .loggerName("com.white.service.UserServiceImpl")
                .messages("UserServiceImpl:")
                .messages("roleName:\t"+userQueryVO.getRoleName())
                .messages("nickName:\t"+userQueryVO.getNickName())
                .messages("currentPageNumber:\t"+userQueryVO.getCurrentPageNumber())
                .messages("pageSize:\t"+userQueryVO.getPageSize())
                .output();

        userQueryVO.setCurrentPageNumber((userQueryVO.getCurrentPageNumber()-1)*userQueryVO.getPageSize());
        LoggerInfo.initialization()
                .loggerName("com.white.service.UserServiceImpl")
                .messages("UserServiceImpl:")
                .messages("SQL 分页查询语句limit startIndex,pagesize")
                .messages("SQL 分页查询参数startIndex:\t"+userQueryVO.getCurrentPageNumber())
                .messages("SQL 分页查询参数pageSize:\t"+userQueryVO.getPageSize())
                .output();

//        return this.baseMapper.getUserByCondition(userQueryVO);
        return userMapper.getUserByCondition(userQueryVO);
    }
}

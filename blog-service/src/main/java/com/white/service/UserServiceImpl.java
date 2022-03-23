package com.white.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.UserService;
import com.white.dto.UserDTO;
import com.white.entity.User;
import com.white.mapper.UserMapper;
import com.white.vo.UserQueryVO;
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

    @Override
    public List<String> getUserRolesByUserId(Integer id) {
        return this.baseMapper.getUserRolesByUserId(id);
    }

    @Override
    public List<UserDTO> getUserByCondition(UserQueryVO userQueryVO) {
        //前端传回的数据是用户列表的当前页码
        //而后端查询需要的是startIndex，即从数据库列表中的第几个开始，初始值为0
        //将前端数据转换成后端数据

        Integer sqlQueryNumber = (userQueryVO.getCurrentPageNumber()-1)*userQueryVO.getPageSize();
        userQueryVO.setSqlQueryNumber(sqlQueryNumber);
        return this.baseMapper.getUserByCondition(userQueryVO);
    }
}

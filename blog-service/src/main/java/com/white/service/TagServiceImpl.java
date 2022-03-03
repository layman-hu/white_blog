package com.white.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.api.TagService;
import com.white.entity.Tag;
import com.white.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<String> getTagListByArticleId(Integer articleId) {
        return this.baseMapper.getTagListByArticleId(articleId);
    }
}

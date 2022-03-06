package com.white.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.entity.Tag;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
public interface TagService extends IService<Tag> {
    /**
     * 通过文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Integer> getTagIdListByArticleId(Integer articleId);
}

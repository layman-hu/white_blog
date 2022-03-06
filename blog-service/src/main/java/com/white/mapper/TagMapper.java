package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author white
 * @since 2022-03-02
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    List<Integer> getTagIdListByArticleId(@Param("articleId") Integer articleId);
}

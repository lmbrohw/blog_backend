package com.xjtu.blog_backend.mapper;

import com.xjtu.blog_backend.entity.SiteSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface SiteSettingMapper extends BaseMapper<SiteSetting> {

    List<SiteSetting> getList();
}

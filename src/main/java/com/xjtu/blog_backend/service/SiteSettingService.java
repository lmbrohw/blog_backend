package com.xjtu.blog_backend.service;

import com.xjtu.blog_backend.entity.SiteSetting;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface SiteSettingService extends IService<SiteSetting> {

    Map<String, List<SiteSetting>> getList();

    Map<String, Object> getSiteInfo();
}

package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.config.RedisKeyConfig;
import com.xjtu.blog_backend.entity.SiteSetting;
import com.xjtu.blog_backend.entity.bean.Badge;
import com.xjtu.blog_backend.entity.bean.Copyright;
import com.xjtu.blog_backend.entity.bean.Favorite;
import com.xjtu.blog_backend.entity.vo.Introduction;
import com.xjtu.blog_backend.mapper.SiteSettingMapper;
import com.xjtu.blog_backend.service.RedisService;
import com.xjtu.blog_backend.service.SiteSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjtu.blog_backend.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@Service
public class SiteSettingServiceImpl extends ServiceImpl<SiteSettingMapper, SiteSetting> implements SiteSettingService {
    @Autowired
    SiteSettingMapper siteSettingMapper;
    @Autowired
    RedisService redisService;

    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\"");

    @Override
    public Map<String, List<SiteSetting>> getList() {
        List<SiteSetting> siteSettings = siteSettingMapper.getList();
        Map<String, List<SiteSetting>> map = new HashMap<>();
        List<SiteSetting> type1 = new ArrayList<>();
        List<SiteSetting> type2 = new ArrayList<>();
        List<SiteSetting> type3 = new ArrayList<>();
        for (SiteSetting s : siteSettings) {
            if (s.getType() == 1) {
                type1.add(s);
            } else if (s.getType() == 2) {
                type2.add(s);
            } else if (s.getType() == 3) {
                type3.add(s);
            }
        }
        map.put("type1", type1);
        map.put("type2", type2);
        map.put("type3", type3);
        return map;
    }

    @Override
    public Map<String, Object> getSiteInfo() {
        String redisKey = RedisKeyConfig.SITE_INFO_MAP;
        Map<String, Object> siteInfoMapFromRedis = redisService.getMapByValue(redisKey);
        if (siteInfoMapFromRedis != null) {
            return siteInfoMapFromRedis;
        }
        List<SiteSetting> siteSettings = siteSettingMapper.getList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> siteInfo = new HashMap<>();
        List<Badge> badges = new ArrayList<>();
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        for (SiteSetting s : siteSettings) {
            if (s.getType() == 1) {
                if ("copyright".equals(s.getNameEn())) {
                    Copyright copyright = JacksonUtils.readValue(s.getValue(), Copyright.class);
                    siteInfo.put(s.getNameEn(), copyright);
                } else {
                    siteInfo.put(s.getNameEn(), s.getValue());
                }
            } else if (s.getType() == 2) {
                Badge badge = JacksonUtils.readValue(s.getValue(), Badge.class);
                badges.add(badge);
            } else if (s.getType() == 3) {
                if ("avatar".equals(s.getNameEn())) {
                    introduction.setAvatar(s.getValue());
                } else if ("name".equals(s.getNameEn())) {
                    introduction.setName(s.getValue());
                } else if ("github".equals(s.getNameEn())) {
                    introduction.setGithub(s.getValue());
                } else if ("qq".equals(s.getNameEn())) {
                    introduction.setQq(s.getValue());
                } else if ("bilibili".equals(s.getNameEn())) {
                    introduction.setBilibili(s.getValue());
                } else if ("netease".equals(s.getNameEn())) {
                    introduction.setNetease(s.getValue());
                } else if ("email".equals(s.getNameEn())) {
                    introduction.setEmail(s.getValue());
                } else if ("favorite".equals(s.getNameEn())) {
                    Favorite favorite = JacksonUtils.readValue(s.getValue(), Favorite.class);
                    favorites.add(favorite);
                } else if ("rollText".equals(s.getNameEn())) {
                    Matcher m = PATTERN.matcher(s.getValue());
                    while (m.find()) {
                        rollTexts.add(m.group(1));
                    }
                }
            }
        }
        introduction.setFavorites(favorites);
        introduction.setRollText(rollTexts);
        map.put("introduction", introduction);
        map.put("siteInfo", siteInfo);
        map.put("badges", badges);
        redisService.saveMapToValue(redisKey, map);
        return map;
    }
}

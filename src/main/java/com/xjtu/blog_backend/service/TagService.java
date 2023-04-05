package com.xjtu.blog_backend.service;

import com.xjtu.blog_backend.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface TagService extends IService<Tag> {

    List<Tag> getTagListNotId();
}

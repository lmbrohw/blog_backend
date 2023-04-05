package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.entity.Comment;
import com.xjtu.blog_backend.mapper.CommentMapper;
import com.xjtu.blog_backend.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

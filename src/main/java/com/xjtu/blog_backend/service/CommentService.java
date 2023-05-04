package com.xjtu.blog_backend.service;

import com.xjtu.blog_backend.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjtu.blog_backend.entity.vo.PageComment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sun ZhongZheng
 * @since 2023-05-04
 */
public interface CommentService extends IService<Comment> {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    void updateCommentPublishedById(Long commentId, Boolean published);

    void updateCommentNoticeById(Long commentId, Boolean notice);

    void deleteCommentById(Long commentId);

    void deleteCommentsByBlogId(Long blogId);

    void updateComment(Comment comment);

    int countByPageAndIsPublished(Integer page, Long blogId);

    void saveComment(Comment comment);
}

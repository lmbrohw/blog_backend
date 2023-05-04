package com.xjtu.blog_backend.mapper;

import com.xjtu.blog_backend.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjtu.blog_backend.entity.vo.PageComment;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<Comment> getListByParentCommentId(Long parentCommentId);

    List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    int updateCommentPublishedById(Long commentId, Boolean published);

    int updateCommentNoticeById(Long commentId, Boolean notice);

    int deleteCommentById(Long commentId);

    int deleteCommentsByBlogId(Long blogId);

    int updateComment(Comment comment);

    int countByPageAndIsPublished(Integer page, Long blogId);

    int countComment();

    int saveComment(Comment comment);
}

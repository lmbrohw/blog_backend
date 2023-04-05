package com.xjtu.blog_backend.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blogId;

    private Long tagId;


}

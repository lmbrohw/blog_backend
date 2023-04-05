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
public class About implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nameEn;

    private String nameZh;

    private String value;


}

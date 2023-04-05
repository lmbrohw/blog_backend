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
public class CityVisitor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 独立访客数量
     */
    private Integer uv;


}

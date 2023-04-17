package com.xjtu.blog_backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Huwwww
 * @Date: 2023/4/17 15:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogView {
    private Long id;
    private Integer views;
}

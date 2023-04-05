package com.xjtu.blog_backend.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 22:39
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewBlog {
    private Long id;
    private String title;
    private String password;
    private Boolean privacy;
}

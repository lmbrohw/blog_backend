package com.xjtu.blog_backend.entity.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 16:54
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Favorite {
    private String title;
    private String content;
}

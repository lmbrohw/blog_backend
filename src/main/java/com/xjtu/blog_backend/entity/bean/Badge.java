package com.xjtu.blog_backend.entity.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 16:55
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Badge {
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;
}

package com.xjtu.blog_backend.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 23:57
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RandomBlog {
    private Long id;
    private String title;//文章标题
    private String firstPicture;//文章首图，用于随机文章展示
    private Date createTime;//创建时间
    private String password;//文章密码
    private Boolean privacy;//是否私密文章
}

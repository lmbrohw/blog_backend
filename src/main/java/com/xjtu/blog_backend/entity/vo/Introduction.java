package com.xjtu.blog_backend.entity.vo;

import com.xjtu.blog_backend.entity.bean.Favorite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 16:53
 **/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Introduction {
    private String avatar;
    private String name;
    private String github;
    private String qq;
    private String bilibili;
    private String netease;
    private String email;

    private List<String> rollText = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();

}

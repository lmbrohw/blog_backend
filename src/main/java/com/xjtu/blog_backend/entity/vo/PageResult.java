package com.xjtu.blog_backend.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 16:49
 **/
@Data
@NoArgsConstructor
public class PageResult<T> {
    private Integer totalPage;//总页数
    private List<T> list;//数据

    public PageResult(Integer totalPage, List<T> list) {
        this.totalPage = totalPage;
        this.list = list;
    }
}

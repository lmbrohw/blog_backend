package com.xjtu.blog_backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装响应结果
 * @Author: Huwwww
 * @Date: 2023/4/5 16:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    private Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public static Result ok(String msg, Object data){
        return new Result(200, msg, data);
    }

    public static Result ok(String msg){
        return new Result(200, msg);
    }

    public static Result error(String msg){
        return new Result(500, msg);
    }

    public static Result error() {
        return new Result(500, "异常错误");
    }

    public static Result create(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result create(Integer code, String msg) {
        return new Result(code, msg);
    }
}

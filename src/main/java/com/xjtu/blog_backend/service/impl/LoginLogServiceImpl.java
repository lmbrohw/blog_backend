package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.entity.LoginLog;
import com.xjtu.blog_backend.mapper.LoginLogMapper;
import com.xjtu.blog_backend.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}

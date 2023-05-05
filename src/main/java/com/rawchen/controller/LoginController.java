package com.rawchen.controller;

import com.rawchen.entity.User;
import com.rawchen.model.dto.LoginInfo;
import com.rawchen.model.vo.Result;
import com.rawchen.service.UserService;
import com.rawchen.util.HashUtils;
import com.rawchen.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前台登录
 * @Date: 2020-09-02
 */
@RestController
public class LoginController {
	@Autowired
	UserService userService;

	/**
	 * 登录成功后，签发博主身份Token
	 *
	 * @param loginInfo
	 * @return
	 */
	@PostMapping("/login")
	public Result login(@RequestBody LoginInfo loginInfo) {
		User user = userService.findUserByUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
		if (!"ROLE_admin".equals(user.getRole())) {
			return Result.create(403, "无权限");
		}
		user.setPassword(null);
		String jwt = JwtUtils.generateToken("admin:" + user.getUsername());
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("token", jwt);
		return Result.ok("登录成功", map);
	}

	@PostMapping("/admin/register")
	public Result register(@RequestBody LoginInfo loginInfo) {
		User user = new User();
		user.setUsername(loginInfo.getUsername());
		String hashPassword = HashUtils.getBC( loginInfo.getPassword());
		user.setPassword(hashPassword);
		Date date = new Date();
		user.setCreateTime(date);
		user.setAvatar("/img/avatar.jpg");
		user.setRole("ROLE_admin");
		System.out.println(hashPassword);
		if (HashUtils.matchBC( loginInfo.getPassword(), hashPassword)){
			System.out.println("成功");
		}
		if (userService.insertUser(user) == 0 ){
			return Result.error("失败");
		}
//		return Result.ok("成功");

//	}
		return Result.ok("成功");
	}
}

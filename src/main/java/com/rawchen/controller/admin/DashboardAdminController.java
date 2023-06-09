package com.rawchen.controller.admin;

import com.rawchen.config.RedisKeyConfig;
import com.rawchen.entity.CityVisitor;
import com.rawchen.model.vo.BlogIdAndTitle;
import com.rawchen.model.vo.Result;
import com.rawchen.service.BlogService;
import com.rawchen.service.CommentService;
import com.rawchen.service.DashboardService;
import com.rawchen.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 后台管理仪表盘
 * @Date: 2020-10-08
 */
@RestController
@RequestMapping("/admin")
public class DashboardAdminController {
	@Autowired
	DashboardService dashboardService;
	@Autowired
	RedisService redisService;
	@Autowired
	CommentService commentService;
	@Autowired
	BlogService blogService;

	@GetMapping("/dashboard")
	public Result dashboard() {
		int todayPV = dashboardService.countVisitLogByToday();
		int todayUV = redisService.countBySet(RedisKeyConfig.IDENTIFICATION_SET);
		int blogCount = dashboardService.getBlogCount();
		int commentCount = dashboardService.getCommentCount();
		Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
		Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();
		Map<String, List> visitRecordMap = dashboardService.getVisitRecordMap();
		List<CityVisitor> cityVisitorList = dashboardService.getCityVisitorList();

		Map<String, Object> map = new HashMap<>();
		map.put("pv", todayPV);
		map.put("uv", todayUV);
		map.put("blogCount", blogCount);
		map.put("commentCount", commentCount);
		map.put("category", categoryBlogCountMap);
		map.put("tag", tagBlogCountMap);
		map.put("visitRecord", visitRecordMap);
		map.put("cityVisitor", cityVisitorList);

		//		根据博客浏览量自动推荐博客
		List<BlogIdAndTitle> idAndTitleList = blogService.getIdAndTitleList();
		for (BlogIdAndTitle blogIdAndTitle : idAndTitleList) {
			Long blogId = blogIdAndTitle.getId();
			if (commentService.countByPageAndIsPublished(0, blogId) > 8) {
				blogService.updateBlogRecommendById(blogId, true);
				System.out.println("修改成功！");
			}
		}

		return Result.ok("获取成功", map);
	}
}

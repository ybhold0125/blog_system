package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.domain.Article;
import com.example.service.IArticleService;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private IArticleService articleService;
	
	//博客首页，会自动跳转到文章页
	@GetMapping("/")
	private String index(HttpServletRequest request) {
		return this.index(request, 1, 5);
	}
	
	//文章页
	@GetMapping("/page/{p}")
	public String index(HttpServletRequest request, @PathVariable("p") int page, 
			@RequestParam(value = "count", defaultValue = "5") int count) {
		PageInfo<Article> articles = articleService.selectArticleWithPage(page, count);
		//获取文章热度统计信息
		List<Article> articleList = articleService.getHeatArticles();
		request.setAttribute("articles", articles);
		request.setAttribute("articleList", articleList);
		logger.info("分页获取文章信息：页码" + page + "，条数" + count );
		return "client/index";
	}
}

package com.example.service;

import java.util.List;

import com.example.model.domain.Article;
import com.github.pagehelper.PageInfo;

public interface IArticleService {
	//分页查询文章列表
	public PageInfo<Article> selectArticleWithPage(Integer page, Integer count);
	
	//统计热度排名前十的文章信息
	public List<Article> getHeatArticles();
}

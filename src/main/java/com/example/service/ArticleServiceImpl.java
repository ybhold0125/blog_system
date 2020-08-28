package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ArticleMapper;
import com.example.dao.StatisticMapper;
import com.example.model.domain.Article;
import com.example.model.domain.Statistic;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private StatisticMapper statisticMapper;
	
	@Override
	public PageInfo<Article> selectArticleWithPage(Integer page, Integer count) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, count);
		List<Article> articleList = articleMapper.selectArticleWithPage();
		//封装文章统计数据
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
			article.setHits(statistic.getHits());
			article.setCommentsNum(statistic.getCommentsNum());
		}
		PageInfo<Article> pageInfo = new PageInfo<>(articleList);
		return pageInfo;
	}

	@Override
	public List<Article> getHeatArticles() {
		List<Statistic> list = statisticMapper.getStatistic();
		List<Article> articleList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Article article = articleMapper.selectArticleWithId(list.get(i).getArticleId());
			article.setHits(list.get(i).getHits());
			article.setCommentsNum(list.get(i).getCommentsNum());
			articleList.add(article);
			if(i>=9) {
				break;
			}
		}
		return articleList;
	}

}

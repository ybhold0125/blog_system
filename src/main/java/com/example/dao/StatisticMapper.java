package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.domain.Article;
import com.example.model.domain.Statistic;

@Mapper
public interface StatisticMapper {
	//新增文章对应的统计信息
	@Insert("INSERT INTO t_statistic(article_id,hits,comments_num) VALUES (#{id},0,0)")
	public void addStatistic(Article article);
	
	//根据文章id查询点击量和评论量相关信息
	@Select("SELECT * FROM t_statistic WHERE article_id=#{articleId}")
	public Statistic  selectStatisticWithArticleId(Integer articleId);
	
	//通过文章id更新点击量
	@Update("UPDATE t_statistic SET hits=#{hits} WHERE article_id=#{articleId}")
	public void updateArticleHitsWithId(Statistic statistic);
	
	//根据文章id更新评论量
	@Update("UPDATE t_statistic SET comments_num=#{comments_num} WHERE article_id=#{articleId}")
	public void updateArticlecommentsWithId(Statistic statistic);
	
	//根据文章id删除统计数据
	@Delete("DELETE FROM t_statistic WHERE article_id=#{aid}")
	public void deleteStatisticWithId(Integer aid);
	
	//统计文章热度信息
	@Select("SELECT * FROM t_statistic WHERE hits != '0'ORDER BY hits DESC,comments_num DESC")
	public List<Statistic> getStatistic();
	
	//统计博客文章总访问量
	@Select("SELECT SUM(hits) FROM t_statistic")
	public Long getTotalVisit();
	
	//统计博客文章总评论量
	@Select("SELECT SUM(comments_num) FROM t_statistic")
	public Long getTotalComment();
} 

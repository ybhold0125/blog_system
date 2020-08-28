package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.model.domain.Article;

@Mapper
public interface ArticleMapper {
	//根据id查询文章信息
	@Select("SELECT * FROM t_article WHERE id=#{id}")
	public Article selectArticleWithId(Integer id);
	
	//发表文章，同时使用@Options注解获取自动生成的主键id
	@Insert("INSERT INTO t_article(title,created,modified,tags,categories,"
			+ "allow_comment,thumbnail,content) VALUES (#{title},#{created},"
			+ "#{modified},#{tags},#{categories},#{allow_comment},#{thumbnail},#{content)}")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public Integer publishArticle(Article article);
	
	//文章分页查询
	@Select("SELECT * FROM t_article ORDER BY id DESC")
	public List<Article> selectArticleWithPage();
	
	//通过id删除文章
	@Delete("DELETE FROM t_article WHERE id=#{id}")
	public void deleteArticleWithId(Integer id);
	
	//站点服务统计，统计文章数量
	@Select("SELECT COUNT(1) FROM t_article")
	public Integer countArticle();
	
	//通过id更新文章
	public Integer updateArticleWithId(Article article);
}

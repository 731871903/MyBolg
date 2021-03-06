package com.yc.S3Lwcblog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yc.S3Lwcblog.bean.Article;
import com.yc.S3Lwcblog.bean.User;
import com.yc.S3Lwcblog.dao.ArticleMapper;

@Controller
public class ArticleAction {
	@Resource
	private ArticleMapper am;
	
	
	@GetMapping("toAddArticle")
	public String toAddArticle() {
		return "addArticle";
	}
	
	@PostMapping("addArticle")
	public String addArticle(Article article, @SessionAttribute User loginedUser) {
		/**
		 * 业务层暂时省略, 字段验证
		 */
		// 作者名字
		article.setAuthor(loginedUser.getName());
		am.insert(article);
		// 响应重定向跳转到文章详情
		return "redirect:article.html?id=" + article.getId();
	}
	
}

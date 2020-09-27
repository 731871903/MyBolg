package com.yc.S3Lwcblog.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.yc.S3Lwcblog.bean.Article;
import com.yc.S3Lwcblog.bean.Category;
import com.yc.S3Lwcblog.dao.ArticleMapper;
import com.yc.S3Lwcblog.dao.CategoryMapper;
import com.yc.S3Lwcblog.dao.LinkMapper;


@Controller
public class IndexAction {
	@Resource
	private ArticleMapper am;
	@Resource
	private CategoryMapper cm;
	@Resource
	private LinkMapper lk;


	
	@GetMapping(path= {"index","index.html","/"})
	//SpringMVC 使用一个Model对象传递数据给页面，Model通过方法参数注入
	public String index(Model m,@RequestParam(defaultValue="1")int page) {
		//分页查询设置必须在查询方法执行前设置
		PageHelper.startPage(page,5);
		List<Article> newArticles=am.selectNewArticle();
		List<Category> newCategory=cm.selectAll();
		//讲查询出的数据添加到model中 发送给页面
		m.addAttribute("newArticles",newArticles);
		m.addAttribute("newCategory",newCategory);

		
		return "index";
	}
	@GetMapping(path= {"article.html"})
	public String article(int id,Model m) {
		
		m.addAttribute("article",am.selectById(id));
		return "article";
	}
	
	@GetMapping(path= {"links.html"})
	public String link(Model m) {
		
		m.addAttribute("links",lk.link());
		return "links";
	}
	
	@GetMapping(path= {"category.html"})
	public String cate(Model m,int id,@RequestParam(defaultValue="1")int page) {
		PageHelper.startPage(page,5);
		m.addAttribute("cateArticle",am.selectcateById(id));
		List<Category> newCategory=cm.selectAll();
		//讲查询出的数据添加到model中 发送给页面
		m.addAttribute("newCategory",newCategory);

		return "category";
	}

}

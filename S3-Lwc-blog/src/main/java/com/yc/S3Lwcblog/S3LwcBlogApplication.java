package com.yc.S3Lwcblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yc.S3Lwcblog.web.LoginInterceptor;

@SpringBootApplication
@MapperScan("com.yc.S3Lwcblog.dao")
public class S3LwcBlogApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(S3LwcBlogApplication.class, args);
	}


@Override
public void addInterceptors(InterceptorRegistry registry) {
	
	registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/toAddArticle","/addArticle");
}	
	
	
}

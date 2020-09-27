package com.yc.S3Lwcblog;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.yc.S3Lwcblog.bean.Category;
import com.yc.S3Lwcblog.bean.User;
import com.yc.S3Lwcblog.biz.BizException;
import com.yc.S3Lwcblog.biz.UserBiz;
import com.yc.S3Lwcblog.dao.CategoryMapper;



@SpringBootTest
class S3LwcBlogApplicationTests {
	@Resource
	private CategoryMapper cm;
	@Resource
	private UserBiz ub;
	@Test
	void contextLoads() {
		
		
			for (Category c : cm.selectAll()) {
				System.out.println(c.getName());
			}
		
	}
	@Test
	void test() {
		User user=new User();
		
		user.setAccount("zhangsan");
		user.setPwd("123456");
		
		try {
			User dbuser=ub.login(user);
			Assert.notNull(dbuser,"user不能为空");
			} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.hasText(e.getMessage(),"dbuser不能为空");

		}
	}

}

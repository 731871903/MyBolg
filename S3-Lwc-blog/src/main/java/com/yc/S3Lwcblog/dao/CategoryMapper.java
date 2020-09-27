package com.yc.S3Lwcblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.S3Lwcblog.bean.Category;



/**
 * 注解(简单sql)+XML（复杂sQL）配置映射
 * @author 炜城
 *
 */
public interface CategoryMapper {

	@Select("select * from category")
	List<Category> selectAll();
	
	
	@Select("select * from category where id=#{id}")
//	@Results(id="cateAct",value={
//			@Result(column ="sort",property="article",
//					many=@Many(select ="com.yc.S3Lwcblog.dao.ArticleMapper.selectcateById"))
//	})
	Category selectById(int id);
	

}

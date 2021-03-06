package com.yc.S3Lwcblog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yc.S3Lwcblog.bean.User;

public interface UserMapper {
	@Select("select count(*) from user where account =#{account}")
	int countByAccount(User user);

	@Select("select * from user where account =#{account} and pwd=#{pwd}")
	User selectByAccountAndPwd(User user);
	
	@Insert("insert into user values(null,#{name},#{account},#{pwd},#{phone},#{email},#{head},#{createtime},#{status},#{type})")
	void insert(User user);
}

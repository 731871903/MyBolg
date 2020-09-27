package com.yc.S3Lwcblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yc.S3Lwcblog.bean.Link;

public interface LinkMapper {
	
	@Select("select * from flink")
	List<Link> link();
}

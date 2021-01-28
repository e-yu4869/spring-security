package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userRepository {

	@Select("SELECT userId FROM e_userInfo WHERE userName = #{userName}")
	String findById(String userName);
	
	@Select("SELECT userName FROM e_userInfo WHERE userName = #{userName}")
	String findByName(String userName);
	
	@Select("SELECT password FROM e_userInfo WHERE userName = #{userName}")
	String findByPass(String userName);
	
	@Select("SELECT admin FROM e_userInfo WHERE userName = #{userName}")
	String findByAdmin(String userName);
	
}

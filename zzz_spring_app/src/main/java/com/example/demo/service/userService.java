package com.example.demo.service;


public interface userService {

	String findById(String userId);
	
	String findByName(String userName);
	
	String findByPass(String password);
	
	String findByAdmin(String admin);
	
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.userRepository;


@Service
@Transactional
public class userServiceImpl implements userService {

	@Autowired
	private userRepository userRepo;
	
	public String findById(String userId) {
		return userRepo.findById(userId);
	}
	
	public String findByName(String userName) {
		return userRepo.findByName(userName);
	}
	
	public String findByPass(String password) {
		return userRepo.findByPass(password);
	}
	
	public String findByAdmin(String admin) {
		return userRepo.findByAdmin(admin);
	}
	
}

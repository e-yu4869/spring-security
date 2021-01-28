package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.userData;

@Service
public class userDetailServiceImpl implements UserDetailsService{

	@Autowired
	private userService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		userData userData = new userData();
		userData.setUserId(userService.findById(userName));
		userData.setUserName(userService.findByName(userName));
		userData.setPassword(userService.findByPass(userName));
		userData.setAdmin(userService.findByAdmin(userName));
		
		
		if(userData.getUserName() == null || userData.getPassword() == null) {
			throw new UsernameNotFoundException("user:"+ userName + "was not founf in the database");
		}
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(userData.getAdmin());
		
		if(authority.equals(null) || authority.equals("")) {
			throw new BadCredentialsException("Authentication Error");
		}
		
		grantList.add(authority);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserDetails userDetails = (UserDetails)new User(userData.getUserName(), encoder.encode(userData.getPassword()), grantList);
		
		return userDetails;
		
	}
	
}

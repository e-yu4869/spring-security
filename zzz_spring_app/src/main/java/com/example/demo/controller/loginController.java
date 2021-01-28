package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.sessionData;

@Controller
@RequestMapping("/login")
public class loginController {
	
	@Autowired
	private sessionData sessionData;
	
	//ログイン初期表示
	@GetMapping
	public String login_init() {
		return "html/login";
	}

	//認証成功時
	@PostMapping("/top")
	public String login_success(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		model.addAttribute("name",name);
		
		sessionData.setUserName(name);
		
		return "html/top";
	}
	
	@GetMapping("/error")
	public String loginError(Model model) {
		
		
		
		model.addAttribute("errMsg", "Invlid userName or password ");
		return "html/login";
		
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		
		model.addAttribute("logout", "logout success!");
		
		return "html/login";
	}
	
}

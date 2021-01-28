package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/return")
public class commonController {

	@RequestMapping(path = "/top" , method = RequestMethod.GET)
	public String returnBack() {
		
		return "/html/top";
		
	}
	
}

package com.example.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainView() {
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		
		System.out.println("B admin is :"+b.encode("admin"));
		
		return "MainView";
	}

	
	
}

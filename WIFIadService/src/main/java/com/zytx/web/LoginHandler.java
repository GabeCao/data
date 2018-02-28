package com.zytx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginHandler {

	@RequestMapping(method=RequestMethod.POST,value="login")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password,
						@RequestParam("role") String role,Model model) {
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("role", role);
		return "admin";
	}
}

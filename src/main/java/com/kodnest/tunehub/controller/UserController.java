package com.kodnest.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;
@Controller
public class UserController {
	@Autowired
	UserServiceimpl userserviceimpl;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute User user) {

		//email taken from registration form
		String email = user.getEmail();

		//checking if the email as enterd in registration form is present in DB or not 
		boolean status = userserviceimpl.emailExists(email);
		if(status == false) {
			userserviceimpl.addUsers(user);
			System.out.println("user added");
		}else {
			System.out.println("User already Exists!");
			return "registration";
		}
		return "login";
	}
//	@GetMapping("/registeration")
//	public String register() {
//		return "registration";
//	}
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	
	@PostMapping("/validate")
	public String validateLogin(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
		if(userserviceimpl.validateuser(email,password)== true){
			
			String role=userserviceimpl.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminhome";
			}else {
				
				User user = userserviceimpl.getUser(email);
				boolean userstatus = user.isIspremium();
				model.addAttribute("ispremium", userstatus);
				return "customerhome";
			}	
		}
		else {
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/exploresongs")
	public String exploresongs(String email) {
		return email;


	}
}

package com.sembmarine.usermanagement.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sembmarine.usermanagement.entity.UserDemo;
import com.sembmarine.usermanagement.service.UserManagementService;

@RestController
@CrossOrigin("*")
public class UserDemoController {	 
	
	@Autowired
	UserManagementService userService ;
	
	@PostConstruct
	public void init(){
		userService.addUserRole();
    }
	
	@PostMapping("/createNewUser")
	public UserDemo createNewUser(@RequestBody UserDemo user) {
		return userService.createNewUser(user) ;
	}
	
	@GetMapping("/getAllUser")	
	public List<UserDemo> getAllUser() {
		return userService.getAllUsers() ;
	}
	
	@GetMapping("/getadmindetails")	
	@PreAuthorize("hasRole('Admin')")
	public String getAdminDetails() {
		return "Only Admin Can Access this URL" ;
	}
	
	@GetMapping("/getuserdetails")
	@PreAuthorize("hasRole('User')")	
	public String getUserDetails() {
		return "Only User Can Access this URL" ;
	}
}

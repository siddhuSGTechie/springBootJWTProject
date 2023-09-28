package com.sembmarine.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sembmarine.usermanagement.entity.Role;
import com.sembmarine.usermanagement.service.UserManagementService;

@RestController
public class RoleController {
	
	@Autowired
	UserManagementService userManagementService ;
	
	@PostMapping("/createRole")
	public Role createRole(@RequestBody Role role) {
		return userManagementService.createRole(role) ;
	}
}

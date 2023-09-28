package com.sembmarine.usermanagement.service;

import java.util.List;

import com.sembmarine.usermanagement.entity.Role;
import com.sembmarine.usermanagement.entity.UserDemo;

public interface UserManagementService {
	
	public Role createRole(Role role) ;
	public UserDemo createNewUser(UserDemo user) ;
	public List<UserDemo> getAllUsers() ;
	public void addUserRole() ;
	
}
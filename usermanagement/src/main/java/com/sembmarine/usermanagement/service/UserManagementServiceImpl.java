package com.sembmarine.usermanagement.service;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sembmarine.usermanagement.entity.Role;
import com.sembmarine.usermanagement.entity.UserDemo;
import com.sembmarine.usermanagement.repo.UserManagementRep;
import com.sembmarine.usermanagement.repo.UserRepo;

@Service
public class UserManagementServiceImpl implements UserManagementService{

	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Autowired
	UserManagementRep userManagementRep ;
	
	@Autowired
	UserRepo userRepo ;

	@Override
	public Role createRole(Role role) {
		return userManagementRep.save(role);
	}

	@Override
	public UserDemo createNewUser(UserDemo user) {
		Role role = new Role() ;		
		Set<Role> userRole =new HashSet<Role>();
		
		role.setRole("User");
		role.setDescription("Role as user");
		role.setStatus("Active");

		userRole.add(role) ;
		user.setRole(userRole);
		user.setPassword(getPassword(user.getPassword()));
		return userRepo.save(user);
	}
	
	
	@Override
	public void addUserRole() {		
//		Set<Role> adminRole= new HashSet<Role>() ;
		Role adminRole = new Role() ;
		adminRole.setRole("Admin");
		adminRole.setStatus("Active");
		adminRole.setDescription("Admin role");
		adminRole.setCreatedBy("Admin");
		
		adminRole.setCreatedDate(new Date());
		userManagementRep.save(adminRole) ;
		
		UserDemo adminUser = new UserDemo() ;
		//adminUser.setUsername("siddhumca92@gmail.com");
		adminUser.setUsername("siddharaj.gurusiddha@sembmarine.com");
		adminUser.setFirstname("Siddhu");
		adminUser.setLastname("G");
		adminUser.setDob("13-12-1992");
		adminUser.setCity("ToaPayoh");
		adminUser.setGender("Male");           
		adminUser.setCountry("India");
		adminUser.setPhonenum("+65 88512230");
		adminUser.setPassword(getPassword("siddhu1992"));
		adminUser.setEmail("siddhumca92@gmail.com");
		adminUser.setDepartment("IT");
		adminUser.setPincode("311085");
		
		
		Set<Role> df= new HashSet<Role>() ;
		df.add(adminRole) ;
		adminUser.setRole(df);
		userRepo.save(adminUser) ;
	}

	@Override
	public List<UserDemo>  getAllUsers() {
		return (List<UserDemo>) userRepo.findAll();
	}
	
	public String getPassword(String password) {
		return passwordEncoder.encode(password);
	}
}

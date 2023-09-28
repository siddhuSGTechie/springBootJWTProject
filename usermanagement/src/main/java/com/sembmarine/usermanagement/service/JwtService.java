package com.sembmarine.usermanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sembmarine.usermanagement.entity.JwtRequest;
import com.sembmarine.usermanagement.entity.JwtResponse;
import com.sembmarine.usermanagement.entity.UserDemo;
import com.sembmarine.usermanagement.repo.UserRepo;
import com.sembmarine.usermanagement.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {
	
	@Autowired
	AuthenticationManager authenticationManager ;
	
	@Autowired
	UserRepo userRepo ;
	
	@Autowired
	JwtUtil jwtUtil ;
	
	public JwtResponse createJwtToken(JwtRequest request) throws Exception{
		
		try {
			
			String userName = request.getUserName() ;
			String password = request.getPassword() ;
			authenticate(userName, password) ;	
			UserDetails userDetails = loadUserByUsername(userName) ;
			String jwtToken = jwtUtil.generateToken(userDetails) ;
			UserDemo user =  userRepo.findById(userName).get() ;
			
			return new JwtResponse(user, jwtToken) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	private void authenticate(String userName, String password) throws Exception {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password)) ;
			
		} catch (DisabledException e) {
			throw new Exception("User is Disabled") ;
		}catch (Exception e) {
			throw new Exception("**********Bad Credentials*********");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDemo userdetails = userRepo.findById(username).get() ;
		
		if(userdetails != null) {
			return new User(userdetails.getUsername(), userdetails.getPassword(), getAuthorities(userdetails)) ;
		}else {
			throw new UsernameNotFoundException("*********************User Name is Not Valid*******************") ;
		}
	}

	private Set getAuthorities(UserDemo userdetails) 
	{

		Set authority = new HashSet();
		userdetails.getRole().forEach(role->{
			authority.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
		});
		
		return authority;
	}
}

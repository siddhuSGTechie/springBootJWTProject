package com.sembmarine.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sembmarine.usermanagement.entity.JwtRequest;
import com.sembmarine.usermanagement.entity.JwtResponse;
import com.sembmarine.usermanagement.service.JwtService;

@RestController
@CrossOrigin("*")
public class JwtController {
	
	@Autowired
	JwtService jwtService ;
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("Controller : - "+ jwtRequest);
		return jwtService.createJwtToken(jwtRequest);
	}
}

package com.sembmarine.usermanagement.entity;


public class JwtResponse {
	
	private UserDemo user ;
	private String jwtToken ;
	
	public JwtResponse(UserDemo user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	
	public UserDemo getUser() {
		return user;
	}
	public void setUser(UserDemo user) {
		this.user = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}

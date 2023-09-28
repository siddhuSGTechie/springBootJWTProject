package com.sembmarine.usermanagement.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
	
	private static final String secretKey = "siddhu" ;
	private static final int JWT_TOKEN_VALIDITY = 3600 * 5 ;
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public<T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claims = getAllClaimsFromToken(token) ;
		return claimResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token) ;
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token) ;
		return expiration.before(new Date()) ;
	}
	
	private Date getExpirationDateFromToken(String token) {
		// TODO Auto-generated method stub
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetaisl) {
		Map<String, Object> claims = new HashMap<>() ;		
		return doGenerateToken(claims, userDetaisl.getUsername()) ;
	}
	
		
	//while creating the token -
	//1. Define claims of the token, like issuer, expiration, subject and ID
	//2. Sign the JWT using th HSS12 algorithm and secret key.
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		// TODO Auto-generated method stub
		return Jwts
				
				.builder() 
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY  * 1000))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact() ;
		
	}
	
}

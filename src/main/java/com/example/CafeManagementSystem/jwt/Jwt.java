package com.example.CafeManagementSystem.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Jwt {

	private String secret = "Bithday";

	
	public String extractUsername(String token) {
		return claimsResolver(token, Claims::getSubject);
	}

	public Date exactExpectation(String token) {
		return claimsResolver(token, Claims::getExpiration);
	}

	//pulling out the any data from claim from token
	public <T> T claimsResolver(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return exactExpectation(token).before(new Date());
	}

	 //creation 
	private String createToken(Map<String,Object>claims,String subject)
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+100*60*60*10))
			    .signWith(SignatureAlgorithm.HS256,secret).compact();
	}
	
	private String generationToken(String username,String role)
	{
		Map<String,Object> claims=new HashMap<>();
		claims.put("role", claims);
		return createToken(claims,username);
	}
	
	//validation after creation
	private Boolean validToken(String token,UserDetails userDetails)
	{
		final String userName=extractUsername(token);
		
		return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
}

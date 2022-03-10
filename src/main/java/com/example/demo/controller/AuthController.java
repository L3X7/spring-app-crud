package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.model.AuthDto;
import com.example.demo.dto.model.JwtDto;
import com.example.demo.security.jwt.JwtUtil;
import com.example.demo.service.UserDetails.UserDetailService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("login")
	public @ResponseBody ResponseEntity<?> post(@RequestBody(required = true) AuthDto user) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		JwtDto jwtDto = new JwtDto();
		jwtDto.setToken(token);
		return ResponseEntity.ok(jwtDto);
	}
}

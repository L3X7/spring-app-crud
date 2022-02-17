package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.User.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		ResponseEntity<List<User>> response = null;
		response = ResponseEntity.ok(this.userService.findAll());
		return response;

	}

}

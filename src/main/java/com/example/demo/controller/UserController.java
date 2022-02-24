package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.model.UserDto;
import com.example.demo.service.User.IUserService;

import io.swagger.annotations.Api;


@Api(value = "User Controller", description = "REST API for User")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<UserDto>> findAll() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}
	
	@GetMapping("/hello")
	ResponseEntity<List<String>> hello() {
		List<String> myList = new ArrayList<String>();
		myList.add("Hello");
	    return ResponseEntity.ok(myList);
	}

}

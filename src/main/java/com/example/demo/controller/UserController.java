package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> get(@PathVariable int id) {
		return ResponseEntity.ok(this.userService.get(id));
	}

	@PostMapping
	public @ResponseBody ResponseEntity<UserDto> post(@RequestBody(required = true) UserDto user) {
		return ResponseEntity.ok(this.userService.add(user));
	}

	@PutMapping
	public @ResponseBody ResponseEntity<UserDto> put(@RequestBody(required = true) UserDto user) {
		return ResponseEntity.ok(this.userService.update(user));
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(this.userService.delete(id));
	}

}

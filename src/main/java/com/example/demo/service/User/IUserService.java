package com.example.demo.service.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.model.UserDto;

@Service
public interface IUserService {

	List<UserDto> getAllUsers();
	
	UserDto get(int id);
	
	UserDto add(UserDto user);
	
	UserDto update(UserDto user);
	
	boolean delete(int id);

}

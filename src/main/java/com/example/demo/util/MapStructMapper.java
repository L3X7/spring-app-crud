package com.example.demo.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.model.UserDto;
import com.example.demo.model.User;

@Mapper(
	    componentModel = "spring"
	)
public interface MapStructMapper {

	//User
	UserDto userToUserDto(User user);
	List<UserDto> listUsersToListUsersDto(List<User> users);
	
}

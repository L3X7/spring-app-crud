package com.example.demo.service.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.model.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MapStructMapper;

import lombok.NoArgsConstructor;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;
	 @Autowired
	 private MapStructMapper mapstructMapper;

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> listUsers = new ArrayList<>();
		try {			
			List<User>listEntity = repository.findAllUsers();
			listUsers = mapstructMapper.listUsersToListUsersDto(listEntity);
			return listUsers;
		} catch (Exception e) {
			return listUsers;
		}
	}	

}

package com.example.demo.service.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			List<User> listEntity = repository.findAllUsers();
			listUsers = mapstructMapper.listUsersToListUsersDto(listEntity);
			return listUsers;
		} catch (Exception e) {
			return listUsers;
		}
	}

	@Override
	public UserDto get(int id) {
		UserDto dto = null;
		Optional<User> entity = repository.findById(id);
		if (entity.isPresent()) {
			dto = mapstructMapper.userToUserDto(entity.get());
		}
		return dto;
	}

	@Override
	public UserDto add(UserDto user) {
		UserDto userDto = null;
		try {
			User userEntity = convertUserDtoToUser(user);
			userEntity = repository.save(userEntity);
			userDto = mapstructMapper.userToUserDto(userEntity);
		} catch (Exception e) {
			throw e;
		}

		return userDto;
	}
	
	@Override
	public UserDto update(UserDto userDto) {
		Optional<User> entity = repository.findById(userDto.getId());
		if(entity.isPresent()) {
			User eu = entity.get();
			mapstructMapper.updateUserFromUserDto(userDto, eu);			
			Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
			eu.setModifiedOn(updatedDate);
			try {
				repository.save(eu);
			}catch (Exception e) {
				throw e;
			}
			userDto = mapstructMapper.userToUserDto(eu);
		} else {
			return null;
		}
		return userDto;
	}
	
	@Override
	public boolean delete(int id) {
		Optional<User> entityTemp = repository.findById(id);
		if(entityTemp.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private User convertUserDtoToUser(UserDto user) {
		User uf = new User();
		uf.setUsername(user.getUsername());
		uf.setPassword(user.getPassword());
		uf.setEmail(user.getEmail());
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		uf.setCreatedOn(createdDate);
		return uf;
	}



}

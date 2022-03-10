package com.example.demo.service.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
			User userEntity = mapstructMapper.userDtoToUser(user);
			Timestamp createdDate = new Timestamp(System.currentTimeMillis());
			userEntity.setCreatedOn(createdDate);
			repository.save(userEntity);
			userDto = mapstructMapper.userToUserDto(userEntity);
		} catch (Exception e) {
			throw e;
		}

		return userDto;
	}

	@Override
	public UserDto update(UserDto userDto) {
		Optional<User> entity = repository.findById(userDto.getId());
		if (entity.isPresent()) {
			User eu = entity.get();
			mapstructMapper.updateUserFromUserDto(userDto, eu);
			Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
			eu.setModifiedOn(updatedDate);
			try {
				repository.save(eu);
			} catch (Exception e) {
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
		if (entityTemp.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> entity = repository.findByUsername(username);
		if (entity.isEmpty()) {
			return null;
		}
		User user = entity.get();
		return user;

	}

}

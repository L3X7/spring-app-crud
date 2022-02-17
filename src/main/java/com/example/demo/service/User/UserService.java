package com.example.demo.service.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		List<User> listUsers = new ArrayList<>();
		try {
			var users = repository.findAll();
			users.forEach(listUsers::add);
			return listUsers;
		} catch (Exception e) {
			return listUsers;
		}

	}

}

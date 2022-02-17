package com.example.demo.service.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface IUserService {

	List<User> findAll();

}

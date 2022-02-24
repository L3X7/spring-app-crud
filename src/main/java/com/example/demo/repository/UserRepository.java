package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;


public interface UserRepository extends CrudRepository<User, Serializable> {

	@Transactional(readOnly=true)
	@Query(value = "SELECT u FROM User u")
	public List<User> findAllUsers();
}

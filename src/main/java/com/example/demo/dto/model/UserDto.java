package com.example.demo.dto.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class UserDto {
	private int id;

	private String username;

	private String password;

	private String email;


	private Timestamp createdOn;

	private Timestamp modifiedOn;
}

package com.office.booklink.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
    private UserMapper userMapper;

	public UserService (UserMapper userMapper) {
		this.userMapper = userMapper;
		
	}
	
	public UserDto isUser(UserDto user) {
		log.info("[userService] isUser()");
	
		return userMapper.isUser(user);
	}

	public UserDto addUser(UserDto user) {
		log.info("[userService] addUser()");
		return userMapper.addUser(user);
	}

	public UserDto modifyUser(UserDto user) {
		log.info("[userService] modifyUser()");
		log.info(user);
		return userMapper.modifyUser(user);
		
	}

	public UserDto deleteUser(UserDto user) {
		log.info("[userService] deletefyUser()");
		log.info(user);
		return userMapper.deleteUser(user);
		
	}

}

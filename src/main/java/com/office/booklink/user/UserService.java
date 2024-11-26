package com.office.booklink.user;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	public void isUser(UserDto user) {
		log.info("[userService] isUser()");
		
	}

}

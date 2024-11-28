package com.office.booklink.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.booklink.library.ILibraryMapper;
import com.office.booklink.library.LibraryRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
    private UserDao userDao;

	public UserService (UserDao userDao) {
		this.userDao = userDao;
		
	}
	
	public UserDto isUser(UserDto user) {
		log.info("[userService] isUser()");
	
		return userDao.isUser(user);
	}

	public UserDto addUser(UserDto user) {
		log.info("[userService] addUser()");
		return userDao.addUser(user);
	}

}

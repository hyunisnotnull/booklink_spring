package com.office.booklink.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	UserDto isUser(UserDto user);

	UserDto addUser(UserDto user);

	UserDto modifyUser(UserDto user);

	UserDto deleteUser(UserDto user);
	
	UserDto getid(UserDto admin);

	UserDto getpw(UserDto admin);

	UserDto updatepw(UserDto admin);

}

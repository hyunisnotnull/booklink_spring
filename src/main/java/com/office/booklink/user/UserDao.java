package com.office.booklink.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

	UserDto isUser(UserDto user);

	UserDto addUser(UserDto user);

}

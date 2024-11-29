package com.office.booklink.library.api;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiMapper {

	int getCount(String userIp);

	void addCount(String userIp);

	void addNew(String userIp);

}

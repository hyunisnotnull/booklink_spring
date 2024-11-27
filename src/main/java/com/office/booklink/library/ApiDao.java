package com.office.booklink.library;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiDao {

	int getCount(String userIp);

	void addCount(String userIp);

	void addNew(String userIp);

}

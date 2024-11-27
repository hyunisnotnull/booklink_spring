package com.office.booklink.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ApiService {
	
	
	@Autowired
	private ApiDao apiDao;

	public ApiService (ApiDao apiDao) {
		this.apiDao = apiDao;
		
	}
	
	public int getCount(String userIp) {
		return apiDao.getCount(userIp);
	}

	public void addCount(String userIp) {
		apiDao.addCount(userIp);
	}

	public void addNew(String userIp) {
		apiDao.addNew(userIp);
	}

}

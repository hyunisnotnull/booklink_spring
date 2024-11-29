package com.office.booklink.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ApiService {
	
	
	@Autowired
	private ApiMapper apiMapper;

	public ApiService (ApiMapper apiMapper) {
		this.apiMapper = apiMapper;
		
	}
	
	public int getCount(String userIp) {
		return apiMapper.getCount(userIp);
	}

	public void addCount(String userIp) {
		apiMapper.addCount(userIp);
	}

	public void addNew(String userIp) {
		apiMapper.addNew(userIp);
	}

}

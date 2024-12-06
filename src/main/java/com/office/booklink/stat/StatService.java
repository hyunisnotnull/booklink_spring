package com.office.booklink.stat;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class StatService {
	
	private final StatMapper statMapper;
	
	public StatService(StatMapper statMapper) {
		this.statMapper = statMapper;
	}

	public List<Map<String, Object>> getBookReadByGender() {
		log.info("[StatService] getBookReadByGender()");
		return statMapper.selectBookReadByGender();
	}

}

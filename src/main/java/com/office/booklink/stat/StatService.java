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

	public List<Map<String, Object>> getBookRank(String year, String month, String gender, String ageGroup) {
		log.info("[StatService] getBookRank()");
		return statMapper.getBookRank(year, month, gender, ageGroup);
	}

	public List<Map<String, Object>> getLibraryRank(String year, String month, String gender, String ageGroup,
			String region) {
		log.info("[StatService] getLibraryRank()");
		return statMapper.getLibraryRank(year, month, gender, ageGroup, region);
	}

}

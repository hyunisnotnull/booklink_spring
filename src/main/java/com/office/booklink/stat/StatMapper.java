package com.office.booklink.stat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatMapper {

	List<Map<String, Object>> selectBookReadByGender();

	List<Map<String, Object>> getBookRank(
			@Param("year") String year,
	        @Param("month") String month,
	        @Param("gender") String gender,
	        @Param("ageGroup") String ageGroup);

	List<Map<String, Object>> getLibraryRank(
			@Param("year") String year,
	        @Param("month") String month,
	        @Param("gender") String gender,
	        @Param("ageGroup") String ageGroup,
			@Param("region") String region);

}

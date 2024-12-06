package com.office.booklink.stat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatMapper {

	List<Map<String, Object>> selectBookReadByGender();

}

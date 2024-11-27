package com.office.booklink.library;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LibraryDao {

	List<LibraryDto> searchRegion(String region);

	List<LibraryDto> searchName(String name);

}

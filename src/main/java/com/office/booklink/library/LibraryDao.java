package com.office.booklink.library;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LibraryDao {

	List<LibraryDto> searchRegion(String region);

	List<LibraryDto> searchName(String name);

	List<LibraryDto> getLibrariesWithinRadius(@Param("latitude") double latitude,@Param("longitude") double longitude);

}

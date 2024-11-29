package com.office.booklink.library;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ILibraryMapper {

	List<LibraryDto> searchRegion(String region);

	List<LibraryDto> searchName(String name);


	List<LibraryDto> findByTitle(@Param("title") String title);

	List<LibraryDto> findByTitleAndRegion(@Param("title") String title, @Param("region") String region);

	List<LibraryDto> getLibrariesWithinRadius(@Param("latitude") double latitude,@Param("longitude") double longitude);


}

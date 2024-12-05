package com.office.booklink.library;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LibraryService {

	@Autowired
    private LibraryRepository libraryRepository;
	private ILibraryMapper iLibraryMapper;


	public LibraryService (ILibraryMapper iLibraryMapper) {
		this.iLibraryMapper = iLibraryMapper;
		
	}
	
	public void saveLibraries(List<LibraryEntity> libraries) {
	    try {
	    	
	        libraryRepository.saveAll(libraries);
	    } catch (Exception e) {
	    	for (LibraryEntity libraryEntity : libraries) {
	    		log.info("{} ---> L_TEL: {}", libraryEntity.getL_CODE(), libraryEntity.getL_TEL()); 
	    		log.info("LENGTH: {}", libraryEntity.getL_TEL().length());
	    		
	    	}
	    	log.info(libraries);
	        log.error("Error saving libraries: {}", libraries, e);
	    }
	}

	public List<LibraryDto> searchRegion(String region) {
		log.info("search library with region: {}", region);
		
		return iLibraryMapper.searchRegion(region);
	}

	public List<LibraryDto> searchName(String name) {
		log.info("search library with name: {}", name);
		
		return iLibraryMapper.searchName(name);
	}


	public List<LibraryDto> searchLibraryByName(String title, String region) {
		log.info("searchLibraryByName()");
		
		log.info("title::::{}", title);
		log.info("region::::{}", region);
		
		List<LibraryDto> result;
		
		// region이 null인지 확인하여 검색 조건 결정
        if (region == null || region.isEmpty()) {
        	result = iLibraryMapper.findByTitle(title); // region 없이 검색
        	log.info("findByTitle result::::{}", result);
        	
        } else {
        	result = iLibraryMapper.findByTitleAndRegion(title, region); // title + region 검색
        	
        }
        return result;
	}

	public List<LibraryDto> getNearbyLibraries(@Param("latitude") double latitude,@Param("longitude") double longitude) {
		log.info("Latitude: {}, Longitude: {}", latitude, longitude);
		
	    // DB에서 도서관 데이터 조회
	    List<LibraryDto> libraries = iLibraryMapper.getLibrariesWithinRadius(latitude, longitude);

	    log.info("Retrieved libraries: {}", libraries);

	    return libraries;

	}
	
}

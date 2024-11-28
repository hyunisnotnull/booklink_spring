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
	private LibraryDao libraryDao;


	public LibraryService (LibraryDao libraryDao) {
		this.libraryDao = libraryDao;
		
	}
	
	public void saveLibraries(List<LibraryEntity> libraries) {
        for (LibraryEntity library : libraries) {
            
        	log.info("Saving library: {}", library);
        	
            libraryRepository.save(library);
        }
    }

	public List<LibraryDto> searchRegion(String region) {
		log.info("search library with region: {}", region);
		
		return libraryDao.searchRegion(region);
	}

	public List<LibraryDto> searchName(String name) {
		log.info("search library with name: {}", name);
		
		return libraryDao.searchName(name);
	}

	public List<LibraryDto> getNearbyLibraries(@Param("latitude") double latitude,@Param("longitude") double longitude) {
		log.info("Latitude: {}, Longitude: {}", latitude, longitude);
		
	    // DB에서 도서관 데이터 조회
	    List<LibraryDto> libraries = libraryDao.getLibrariesWithinRadius(latitude, longitude);

	    log.info("Retrieved libraries: {}", libraries);

	    return libraries;
	}
}

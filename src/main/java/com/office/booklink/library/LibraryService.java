package com.office.booklink.library;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<LibraryDto> searchLibraryByName(String title, String region) {
		log.info("searchLibraryByName()");
		
		log.info("title::::{}", title);
		log.info("region::::{}", region);
		
		List<LibraryDto> result;
		
		// region이 null인지 확인하여 검색 조건 결정
        if (region == null || region.isEmpty()) {
        	result = libraryDao.findByTitle(title); // region 없이 검색
        	log.info("findByTitle result::::{}", result);
        	
        } else {
        	result = libraryDao.findByTitleAndRegion(title, region); // title + region 검색
        	
        }
        return result;
	}
}

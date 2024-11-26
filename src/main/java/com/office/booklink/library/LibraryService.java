package com.office.booklink.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LibraryService {

	@Autowired
    private LibraryRepository libraryRepository;

	public void saveLibraries(List<LibraryEntity> libraries) {
        for (LibraryEntity library : libraries) {
            
        	log.info("Saving library: {}", library);
        	
            libraryRepository.save(library);
        }
    }
}

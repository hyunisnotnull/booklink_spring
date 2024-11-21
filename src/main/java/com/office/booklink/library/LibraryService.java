package com.office.booklink.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LibraryService {

	@Autowired
    private LibraryRepository libraryRepository;

    public void saveLibraryData(LibraryDto library) {
        if (library != null && library.getL_CODE() != 0) {
            libraryRepository.save(library);  // 도서관 정보를 DB에 저장
        }
    }
}

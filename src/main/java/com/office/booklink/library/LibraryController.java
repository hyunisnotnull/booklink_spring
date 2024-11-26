package com.office.booklink.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/library")
public class LibraryController {
	
	@Autowired
    private LibraryService libraryService;

	// 도서관 정보 업데이트 API
    @PostMapping("/update")
    public String updateLibrary(@RequestBody List<LibraryEntity> libraries) {
        try {

        	log.info("Received libraries data: {}", libraries);
        	
            libraryService.saveLibraries(libraries);
            return "도서관 데이터가 성공적으로 저장되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "도서관 데이터 저장에 실패했습니다.";
        }
    }
}

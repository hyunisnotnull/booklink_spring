package com.office.booklink.library;

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

	@PostMapping("/update")
    public String updateLibrary(@RequestBody LibraryDto[] libraries) {
        try {
            for (LibraryDto library : libraries) {
                libraryService.saveLibraryData(library);
            }
            return "도서관 데이터 저장 완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "도서관 데이터 저장 실패";
        }
    }
}

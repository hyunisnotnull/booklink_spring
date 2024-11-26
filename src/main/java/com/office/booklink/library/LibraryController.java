package com.office.booklink.library;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/library")
public class LibraryController {
	
	@Autowired
    private LibraryService libraryService;

	// 도서관 정보 업데이트 API
    @PostMapping("/update")
    public String updateLibrary(@RequestBody List<LibraryDto> libraries) {
        try {

        	log.info("Received libraries data: {}", libraries);
        	
            libraryService.saveLibraries(libraries);
            return "도서관 데이터가 성공적으로 저장되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "도서관 데이터 저장에 실패했습니다.";
        }
    }
    
	// 도서관 정보 업데이트 API
    @GetMapping("/region/{region}")
    public String searchRegion(@PathVariable("region") String region) {
    	log.info("searchRegion()");
    	System.out.println(region);
    	
    	List<LibraryDto> libraries = libraryService.searchRegion(region);
    	System.out.println(libraries);
            return null;

    }
    
    @GetMapping("/name/{name}")
    public String searchName(@PathVariable("name") String name) {
    	log.info("searchName()");
    	System.out.println(name);
    	
    	List<LibraryDto> libraries = libraryService.searchName(name);
    	System.out.println(libraries);
            return null;

    }
}

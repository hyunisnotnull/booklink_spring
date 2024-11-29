package com.office.booklink.library;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.office.booklink.library.api.ApiService;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/library")
public class LibraryController {
	
	@Autowired
    private LibraryService libraryService;

	public LibraryController (LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	
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
    

    @GetMapping("/searchByName")
    public ResponseEntity<List<LibraryDto>> searchLibraryByName(
	        @RequestParam("title") String title,
	        @RequestParam(value = "region", required = false) String region) {
    	log.info("searchByName()", title, region);
    	
    	try {
    		// title과 region에 따라 조건을 다르게 검색
    		List<LibraryDto> libraries = libraryService.searchLibraryByName(title, region);
    		
    		log.info("libraries()", libraries);
			
    		return ResponseEntity.ok(libraries);
    		
		} catch (Exception e) {
			log.error("Error during searchLibraryByName:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.emptyList());
		}
    	
    }
    	

    // 대출 가능 여부 도서관 찾기
    @GetMapping("/nearbyLibraries")
    public List<LibraryDto> getNearbyLibraries(@RequestParam("latitude") double latitude, 
                                             @RequestParam("longitude") double longitude) {
        return libraryService.getNearbyLibraries(latitude, longitude);
    }

    
}

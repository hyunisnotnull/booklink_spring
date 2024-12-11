package com.office.booklink.stat;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/stat")
public class StatController {
	
	private final StatService statService;
	
	public StatController(StatService statService) {
		this.statService = statService;
	}
	
	// 읽은 책 가지고 오기
	@GetMapping("/getBookRead")
	public ResponseEntity<Object> getBookReadByGender() {
		log.info("[StatController] getBookReadByGender()");
        try {
            List<Map<String, Object>> data = statService.getBookReadByGender();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch data.");
        }
    }
	
	// 도서 찜 순위 가지고 오기
	@GetMapping("/getBookRank")
	public ResponseEntity<Object> getBookRank(
			@RequestParam("year") String year,
	        @RequestParam("month") String month,
	        @RequestParam("gender") String gender,
	        @RequestParam("ageGroup") String ageGroup) {
		log.info("[StatController] getBookRank()");
        try {
            List<Map<String, Object>> data = statService.getBookRank(year, month, gender, ageGroup);
            log.info("data -> {}", data);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch data.");
        }
    }
	
	// 도서관 찜 순위 가지고 오기
	@GetMapping("/getLibraryRank")
	public ResponseEntity<Object> getLibraryRank(
			@RequestParam("year") String year,
	        @RequestParam("month") String month,
	        @RequestParam("gender") String gender,
	        @RequestParam("ageGroup") String ageGroup,
	        @RequestParam("region") String region) {
		log.info("[StatController] getLibraryRank()");
        try {
            List<Map<String, Object>> data = statService.getLibraryRank(year, month, gender, ageGroup, region);
            log.info("data -> {}", data);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
        	log.info("e -> {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch data.");
        }
    }

}

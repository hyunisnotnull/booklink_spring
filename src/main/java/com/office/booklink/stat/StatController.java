package com.office.booklink.stat;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}

package com.office.booklink.event;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/event")
public class EventController {
	
	private final EventService eventService;
	private final UploadFileService uploadFileService;
	
	public EventController(EventService eventService, UploadFileService uploadFileService) {
		this.eventService = eventService;
		this.uploadFileService = uploadFileService;
	}
	
	@GetMapping("/list")
    public List<EventDto> getEventList() {
		log.info("getEventList()");
        return eventService.getAllEvents();  // 서비스 호출하여 이벤트 목록 반환
    }
	
	@PostMapping("/register")
	public ResponseEntity<String> registerEvent(
	        @RequestParam("title") String title,
	        @RequestParam("event_image") MultipartFile event_image,
	        @RequestParam("url") String url,
	        @RequestParam("e_active") int eActive,
	        @RequestParam("description") String description,
	        @RequestParam("startDate") String startDate,
	        @RequestParam("endDate") String endDate) {
	    log.info("Received event_image");

	    try {
	        // 파일 저장 경로를 업로드 서비스로부터 얻기
	        String eventImagePath = uploadFileService.upload(UUID.randomUUID().toString(), event_image);

	        if (eventImagePath == null) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드에 실패했습니다.");
	        }

	        // EventDto 객체에 데이터 세팅
	        EventDto eventDto = new EventDto();
	        eventDto.setE_title(title);
	        eventDto.setE_image(eventImagePath); // 저장된 파일 경로
	        eventDto.setE_url(url);
	        eventDto.setE_active(eActive);
	        eventDto.setE_desc(description);
	        eventDto.setE_start_date(startDate);
	        eventDto.setE_end_date(endDate);

	        eventService.registerEvent(eventDto);

	        return ResponseEntity.ok("이벤트가 성공적으로 등록되었습니다.");
	    } catch (Exception e) {
	        log.error("Error registering event", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 등록에 실패했습니다.");
	    }
	}
	
	// 이벤트 수정 폼 데이터를 가져오는 API
    @GetMapping("/modify/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable int eventId) {
        try {
        	EventDto eventDto = eventService.getEventById(eventId);  // 이벤트 ID로 이벤트 정보 조회
            return ResponseEntity.ok(eventDto);  // 이벤트 데이터를 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // 오류 발생 시 500 반환
        }
    }

    // 이벤트 수정 데이터 처리 API (PUT)
    @PutMapping("/modifyConfirm/{eventId}")
    public ResponseEntity<String> modifyEvent(@PathVariable int eventId, @RequestBody EventDto eventDto) {
        try {
            eventService.modifyEvent(eventId, eventDto);  // 수정된 이벤트 정보 업데이트
            return ResponseEntity.ok("이벤트가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 수정에 실패했습니다.");
        }
    }

}

package com.office.booklink.event;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// 이벤트 리스트 API
	@GetMapping("/list")
    public List<EventDto> getEventList() {
		log.info("getEventList()");
        return eventService.getAllEvents();  // 서비스 호출하여 이벤트 목록 반환
    }
	
	// 이벤트 등록 API
	@PostMapping("/register")
	public ResponseEntity<String> registerEvent(
	        @RequestParam("title") String title,
	        @RequestParam("event_image") MultipartFile event_image,
	        @RequestParam("url") String url,
	        @RequestParam("e_active") int eActive,
	        @RequestParam("description") String description,
	        @RequestParam("startDate") String startDate,
	        @RequestParam("endDate") String endDate) {

	    try {
	        // 파일 저장 경로를 업로드 서비스로부터 얻기
	        String eventImagePath = uploadFileService.upload(title, event_image);

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
	
	// 이벤트 수정 API
	@GetMapping("/modify/{eventId}")
	public ResponseEntity<EventDto> getEvent(@PathVariable("eventId") int eventId) {
	    try {
	    	EventDto eventDto = eventService.getEventById(eventId);
	        log.info("eventDto :: {}", eventDto);
	        log.info("eventDto ::", eventDto);
	        return ResponseEntity.ok(eventDto);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

    // 이벤트 수정 처리 API
	@PutMapping("/modifyConfirm/{eventId}")
	public ResponseEntity<String> modifyEvent(
	        @PathVariable("eventId") int eventId,
	        @RequestParam("title") String title,
	        @RequestParam(value = "event_image", required = false) MultipartFile event_image,
	        @RequestParam("url") String url,
	        @RequestParam("e_active") int eActive,
	        @RequestParam("description") String description,
	        @RequestParam("startDate") String startDate,
	        @RequestParam("endDate") String endDate) {

	    try {
	        // 기존 이벤트 정보 가져오기
	        EventDto existingEvent = eventService.getEventById(eventId);

	        // 파일이 새로 업로드된 경우, 파일을 업로드하고 경로를 설정
	        String eventImagePath = existingEvent.getE_image(); // 기본값은 기존 이미지

	        if (event_image != null && !event_image.isEmpty()) {
	            // 새 이미지가 업로드되었으면 파일을 저장하고 경로 업데이트
	            eventImagePath = uploadFileService.upload(UUID.randomUUID().toString(), event_image);
	        }

	        // EventDto 객체에 수정된 데이터 세팅
	        EventDto eventDto = new EventDto();
	        eventDto.setE_no(eventId);
	        eventDto.setE_title(title);
	        eventDto.setE_image(eventImagePath); // 새 이미지가 있으면 새 경로, 없으면 기존 경로
	        eventDto.setE_url(url);
	        eventDto.setE_active(eActive);
	        eventDto.setE_desc(description);
	        eventDto.setE_start_date(startDate);
	        eventDto.setE_end_date(endDate);

	        // 이벤트 수정
	        eventService.modifyEvent(eventId, eventDto);

	        return ResponseEntity.ok("이벤트가 성공적으로 수정되었습니다.");
	    } catch (Exception e) {
	        log.error("Error modifying event", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 수정에 실패했습니다.");
	    }
	}

	// 이벤트 상태 변경 API
	@PutMapping("/statusConfirm/{eventId}")
    public ResponseEntity<String> changeEventStatus(@PathVariable("eventId") int eventId, 
                                                    @RequestBody EventDto eventDto) {
        try {
            // 서비스로 상태 변경 요청을 전달
            boolean isUpdated = eventService.changeEventStatus(eventId, eventDto.getE_active());
            
            log.info("changeEventStatus() - eventId: {}, e_active: {}", eventId, eventDto.getE_active());

            if (isUpdated) {
                return ResponseEntity.ok("이벤트 상태가 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상태 변경에 실패했습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상태 변경에 실패했습니다.");
        }
    }
	
	@DeleteMapping("/delete/{eventId}")
	public ResponseEntity<String> deleteEvent(@PathVariable("eventId") int eventId) {
	    try {
	        boolean isDeleted = eventService.deleteEvent(eventId);
	        if (isDeleted) {
	            return ResponseEntity.ok("이벤트가 성공적으로 삭제되었습니다.");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 삭제에 실패했습니다.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 삭제에 실패했습니다.");
	    }
	}


}

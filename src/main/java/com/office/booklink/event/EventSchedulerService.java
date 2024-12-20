package com.office.booklink.event;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EventSchedulerService {

    private final TaskScheduler taskScheduler;
    private final EventService eventService;

    public EventSchedulerService(TaskScheduler taskScheduler, @Lazy EventService eventService) {
        this.taskScheduler = taskScheduler;
        this.eventService = eventService;
    }

    // 이벤트 종료일에 맞춰 자동으로 비활성화 처리
    public void scheduleEventDeactivation(EventDto eventDto) {
        String endDateString = eventDto.getE_end_date();  
        
        // 두 가지 날짜 형식을 위한 포맷터 정의
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localEndDate = null;

        try {
            localEndDate = LocalDateTime.parse(endDateString, formatter1);
        } catch (Exception e) {
            try {
                localEndDate = LocalDateTime.parse(endDateString, formatter2);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid date format: " + endDateString);
            }
        }

        // 한국 시간 (KST, UTC+9)으로 ZonedDateTime 변환
        ZonedDateTime eventEndDate = ZonedDateTime.of(localEndDate, ZoneOffset.ofHours(9));

        // 종료일에 맞춰 비활성화 작업을 예약
        log.info("스케줄링 등록됨: 이벤트 '{}'의 종료일 {}", eventDto.getE_title(), eventEndDate.toString());
        taskScheduler.schedule(() -> deactivateEvent(eventDto), eventEndDate.toInstant());
    }


    // 이벤트 비활성화 작업
    private void deactivateEvent(EventDto eventDto) {
        if (eventDto.getE_active() == 2 || eventDto.getE_active() == 4) {
        	log.info("이미 비활성화된 이벤트입니다 : {} ", eventDto.getE_title());
            return;
        }

        // 상태 변경: 홈 광고는 1에서 2로, 자체 광고는 3에서 4로 변경
        int updatedStatus = (eventDto.getE_active() == 1) ? 2 : (eventDto.getE_active() == 3 ? 4 : eventDto.getE_active());
        
        eventDto.setE_active(updatedStatus);
        eventService.modifyEvent(eventDto.getE_no(), eventDto);

        log.info("이벤트 비활성화 : {} ", eventDto.getE_title());
    }
}

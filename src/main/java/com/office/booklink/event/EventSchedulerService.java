package com.office.booklink.event;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

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
        // 종료 날짜를 ZonedDateTime으로 변환 (형식에 맞게 처리)
        String endDateString = eventDto.getE_end_date();  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        ZonedDateTime eventEndDate = ZonedDateTime.of(LocalDateTime.parse(endDateString, formatter), ZoneOffset.ofHours(9));

        // 종료일에 맞춰 비활성화 작업을 예약
        taskScheduler.schedule(() -> deactivateEvent(eventDto), eventEndDate.toInstant());
    }

    // 이벤트 비활성화 작업
    private void deactivateEvent(EventDto eventDto) {
        if (eventDto.getE_active() == 2 || eventDto.getE_active() == 4) {
            System.out.println("이미 비활성화된 이벤트입니다: " + eventDto.getE_title());
            return;
        }

        // 상태 변경: 홈 광고는 1에서 2로, 자체 광고는 3에서 4로 변경
        int updatedStatus = (eventDto.getE_active() == 1) ? 2 : (eventDto.getE_active() == 3 ? 4 : eventDto.getE_active());
        
        eventDto.setE_active(updatedStatus);
        eventService.modifyEvent(eventDto.getE_no(), eventDto);

        System.out.println("이벤트 비활성화: " + eventDto.getE_title());
    }
}

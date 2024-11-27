package com.office.booklink.event;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EventService {
	
	private final IEventMapper iEventMapper;
	private final EventSchedulerService eventSchedulerService;
	
	public EventService(IEventMapper iEventMapper, EventSchedulerService eventSchedulerService) {
		this.iEventMapper = iEventMapper;
		this.eventSchedulerService = eventSchedulerService;
	}

	public List<EventDto> getAllEvents() {
		log.info("getAllEvents()");
		return iEventMapper.getEventList();
	}

	public void registerEvent(EventDto eventDto) {
		log.info("registerEvent()");
		iEventMapper.insertEvent(eventDto);
		log.info("Registered event ID: {}", eventDto.getE_no());
		eventSchedulerService.scheduleEventDeactivation(eventDto);
	}

	public EventDto getEventById(int eventId) {
	    log.info("getEventById() for eventId: " + eventId);
	    return iEventMapper.getEventById(eventId);
	}

	public void modifyEvent(int eventId, EventDto eventDto) {
		log.info("modifyEvent() eventId:::" + eventId);
		iEventMapper.updateEvent(eventId, eventDto);
		eventSchedulerService.scheduleEventDeactivation(eventDto);
	}

	public boolean changeEventStatus(int eventId, int e_active) {
		try {
			log.info("changeEventStatus() - eventId: {}, e_active: {}", eventId, e_active);
			
			int updatedRows = iEventMapper.updateEventStatus(eventId, e_active);
			
			log.info("updatedRows() : {}", updatedRows);

            return updatedRows > 0;
            
        } catch (Exception e) {
        	log.error("Error in changing event status", e);
            return false;
        }
	}

	public boolean deleteEvent(int eventId) {
	    try {
	        // 해당 eventId로 이벤트 삭제
	        int rowsDeleted = iEventMapper.deleteEvent(eventId);
	        return rowsDeleted > 0;
	    } catch (Exception e) {
	        log.error("Error deleting event", e);
	        return false;
	    }
	}

}

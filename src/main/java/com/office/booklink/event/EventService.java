package com.office.booklink.event;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EventService {
	
	private final IEventMapper iEventMapper;
	
	public EventService(IEventMapper iEventMapper) {
		this.iEventMapper = iEventMapper;
	}

	public List<EventDto> getAllEvents() {
		log.info("getAllEvents()");
		return iEventMapper.getEventList();
	}

	public void registerEvent(EventDto eventDto) {
		log.info("registerEvent()");
		iEventMapper.insertEvent(eventDto);
	}

	public EventDto getEventById(int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyEvent(int eventId, EventDto eventDto) {
		// TODO Auto-generated method stub
		
	}

}

package com.office.booklink.event;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IEventMapper {

	List<EventDto> getEventList();

	void insertEvent(EventDto eventDto);

	EventDto getEventById(int eventId);

	void updateEvent(@Param("eventId") int eventId, @Param("eventDto") EventDto eventDto);

	int updateEventStatus(@Param("eventId") int eventId, @Param("e_active") int e_active);

	int deleteEvent(@Param("eventId") int eventId);

}

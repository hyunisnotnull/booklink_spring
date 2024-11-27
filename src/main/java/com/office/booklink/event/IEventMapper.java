package com.office.booklink.event;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IEventMapper {

	List<EventDto> getEventList();

	void insertEvent(EventDto eventDto);

}

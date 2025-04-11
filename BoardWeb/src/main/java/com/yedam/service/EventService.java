package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

//업무프로세스(service)
public interface EventService {
	List<Map<String, Object>> eventList();
	boolean addEvent(EventVO evo);
	boolean removeEvent(EventVO evo);
	List<Map<String, Object>> cntPerWriter();
	boolean logCreate(Map<String, String> map);
}

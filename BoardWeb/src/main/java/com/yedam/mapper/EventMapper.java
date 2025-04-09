package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventMapper {
    List<Map<String, Object>> selectEvent();
    int insertEvent(EventVO evo);
    int deleteEvent(EventVO evo);
}

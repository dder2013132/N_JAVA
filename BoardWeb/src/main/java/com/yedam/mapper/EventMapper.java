package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventMapper {
    List<Map<String, Object>> selectEvent();
    int insertEvent(EventVO evo);
    int deleteEvent(EventVO evo);
    // 차트
    List<Map<String, Object>> selectWriter();
    // 로그
    int insertLogging(Map<String, String> map);
}

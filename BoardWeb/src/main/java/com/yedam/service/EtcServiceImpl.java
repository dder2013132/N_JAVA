package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.EtcMapper;

public class EtcServiceImpl implements EtcService {
	SqlSession sqlSession = //
			DataSource.getInstance().openSession(true);
	EtcMapper mapper = sqlSession.getMapper(EtcMapper.class);

	@Override
	public List<Map<String, Object>> eventList() {
		return mapper.selectEvent();
	}

	@Override
	public boolean addEvent(Map<String, Object> data) {
		return mapper.insertEvent(data) == 1;
	}

	@Override
	public boolean removeEvent(Map<String, Object> data) {
		return mapper.deleteEvent(data) == 1;
	}

	@Override
	public List<Map<String, Object>> cntPerWriter() {
	    return mapper.selectCntPerWriter();
	}

	@Override
	public void logCreate(Map<String, String> map) {
		mapper.insertLogging(map);
		
	}
	

}

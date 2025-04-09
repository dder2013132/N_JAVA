package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RLDatatable implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/json; charset=utf-8");
		// { "data": [ [21],'댓글내용','user01','날짜'],[],[]...[] ]}
		String bno = req.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		List<Map<String, Object>> list = svc.replyListForDT(Integer.parseInt(bno));
		
		Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(result);
        resp.getWriter().print(json);
	}

}

package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class AddEventControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("utf-8");
        
        String title = req.getParameter("title");
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        System.out.println(title + " " + start + " " + end);
        EventVO evo = new EventVO();
        evo.setTitle(title);
        evo.setStart(start);
        evo.setEnd(end);
        
        Gson gson = new GsonBuilder().create();
        Map<String, Object> map = new HashMap<>();
        
        EventService svc = new EventServiceImpl();
        if (svc.addEvent(evo)) {
//            resp.getWriter().print("{\"retCode\": \"OK\"}");
            map.put("retCode", "OK");
        }
        else {
//            resp.getWriter().print("{\"retCode\": \"NG\"}");
            map.put("retCode", "NG");
        }
        String json = gson.toJson(map);
        resp.getWriter().print(json);
    }

}

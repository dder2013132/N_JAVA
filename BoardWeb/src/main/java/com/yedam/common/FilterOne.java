package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;

// 클라이언트 - 필터 - 서블릿

public class FilterOne implements Filter {
    
    public FilterOne() {
        System.out.println("FilterOne 생성");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("서블릿 실행 전");
        
        String ip = request.getRemoteAddr();
        
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String reqPage = uri.substring(context.length());
        
        Map<String, String> map = new HashMap<>();
        map.put("page", reqPage);
        map.put("host", ip);
        
        // 로그 저장
        EventService svc = new EventServiceImpl();
        svc.logCreate(map);
        
        if (ip.equals("192.168.0.39")) {
            req.getRequestDispatcher("etc/deny.tiles").forward(req, response);
            return;
        }
        
        chain.doFilter(request, response);
        System.out.println("서블릿 실행 후");
    }
    
}

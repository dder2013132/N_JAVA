package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplyControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rno = req.getParameter("rno");
        ReplyService svc = new ReplyServiceImpl();
        if (svc.removeReply(Integer.parseInt(rno))) {
            resp.getWriter().print("{\"retCode\": \"OK\"}");
        }
        else {
            resp.getWriter().print("{\"retCode\": \"NG\"}");
        }
    }

}

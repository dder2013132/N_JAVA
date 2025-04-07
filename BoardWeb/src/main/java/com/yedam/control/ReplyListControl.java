package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * [ { "replyNo": 112, "reply": "내용...", ... }, ... ]
         */
        resp.setContentType("text/html;charset=utf-8");

        String bno = req.getParameter("bno");
        ReplyService svc = new ReplyServiceImpl();
        List<ReplyVO> list = svc.replyList(Integer.parseInt(bno));

        String json = "[";
        for (ReplyVO rep : list) {
            json += "{" //
                    + "\"replyNo\": " + rep.getReplyNo() + "," //
                    + "\"reply\": \"" + rep.getReply() + "\"," //
                    + "\"replyer\": \"" + rep.getReplyer() + "\"," //
                    + "\"replyDate\": \"" + rep.getReplyDate() + "\"," //
                    + "\"boardNo\": " + rep.getBoardNo() //
                    + "},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]";
        
        resp.getWriter().print(json);
    }

}

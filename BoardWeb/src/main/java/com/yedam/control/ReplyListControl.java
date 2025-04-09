package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
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

        int bno = Integer.parseInt(req.getParameter("bno"));
        int page = Integer.parseInt(req.getParameter("page"));
        SearchDTO search = new SearchDTO();
        search.setBoardNo(bno);
        search.setPage(page);
        
        ReplyService svc = new ReplyServiceImpl();
        List<ReplyVO> list = svc.replyList(search);

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

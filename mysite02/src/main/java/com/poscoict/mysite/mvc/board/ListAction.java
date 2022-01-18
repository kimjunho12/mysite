package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int pageCount = ;
//		int currentpage = ;
//		int nextPage = -1; // 없음
//		int startPage = ;
//		int prePage = ;
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.findAll();
		
		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);
	}

}

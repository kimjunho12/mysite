package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");

		Long userNo = authUser.getNo();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int groupNo = Integer.parseInt(request.getParameter("g_no"));
		int orderNo = Integer.parseInt(request.getParameter("o_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));

		BoardVo vo = new BoardVo();
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);
		System.out.println(vo);

		boolean result = new BoardDao().insert(vo);
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}

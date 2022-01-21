package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = new BoardDao().delete(Long.parseLong(request.getParameter("no")));
		String currentPage = request.getParameter("p");
		MvcUtil.redirect(request.getContextPath() + "/board?p=" + currentPage, request, response);
	}

}

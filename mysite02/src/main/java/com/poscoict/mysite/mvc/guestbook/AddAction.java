package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.mysite.vo.GuestbookVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GuestbookVo vo = new GuestbookVo();
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setMessage(request.getParameter("message"));
		new GuestbookDao().insert(vo);

		MvcUtil.redirect(request.getContextPath().concat("/guestbook"), request, response);
	}

}

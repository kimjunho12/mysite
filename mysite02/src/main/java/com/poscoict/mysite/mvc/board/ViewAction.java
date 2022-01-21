package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = dao.findByNo(no);
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		Long user = -1L;
		if (authUser != null) {
			user = authUser.getNo();
		}

		boolean isHit = false;
		if (vo != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (String.format("hit_%s_%s", no, user).equals(cookie.getName())) {
						// 쿠키 있는지 확인 됨
						// ㄴ> hit 안올라가게
						isHit = true;
						break;
					}
				}

				if (isHit != true) {
					Cookie newCookie = new Cookie(String.format("hit_%s_%s", no, user),
							String.valueOf(dao.updateHit(no)));
					newCookie.setPath(request.getContextPath());
					newCookie.setMaxAge(12 * 60 * 60);
					response.addCookie(newCookie);
				}
			}
		}

		request.setAttribute("vo", vo);
		MvcUtil.forward("board/view", request, response);
	}

}

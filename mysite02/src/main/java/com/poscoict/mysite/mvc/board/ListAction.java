package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;
import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {
	private static final int PAGE_PER_VIEW = 5;
	private static final int BOARD_PER_VIEW = 10;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();

		String p = request.getParameter("p");
		String keyWord = request.getParameter("kwd");
		keyWord = StringUtils.isEmptyOrWhitespaceOnly(keyWord) ? "" : keyWord;

		if (StringUtils.isEmptyOrWhitespaceOnly(p)) {
			MvcUtil.redirect(request.getContextPath() + "/board?p=1", request, response);
			return;
		}

		int pageCount = (int) Math.ceil(dao.totalCnt() / BOARD_PER_VIEW);
		int currentPage = Integer.parseInt(p);
		int endPage = (int) (Math.ceil((double) currentPage / PAGE_PER_VIEW)) * PAGE_PER_VIEW;
		int startPage = endPage - PAGE_PER_VIEW + 1;
		int nextPage = (startPage + PAGE_PER_VIEW < pageCount) ? startPage + PAGE_PER_VIEW : startPage;
		int prePage = (startPage - PAGE_PER_VIEW > 0) ? startPage - PAGE_PER_VIEW : startPage;

		Map<String, Integer> pager = new HashMap<>();
		pager.put("pageCount", pageCount);
		pager.put("currentPage", currentPage);
		pager.put("startPage", startPage);
		pager.put("endPage", endPage);
		pager.put("nextPage", nextPage);
		pager.put("prePage", prePage);
		pager.put("bpv", BOARD_PER_VIEW);

		List<BoardVo> list = dao.findAll(pager, keyWord);

		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		MvcUtil.forward("board/list", request, response);
	}

}

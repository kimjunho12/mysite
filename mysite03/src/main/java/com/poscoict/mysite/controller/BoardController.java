package com.poscoict.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(Model model, @RequestParam(name = "p", required = true, defaultValue = "1") Integer p,
			@RequestParam(name = "kwd", required = false, defaultValue = "") String kwd) {

		Map<String, Object> map = boardService.getContentsList(p, kwd);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pager", map.get("pager"));

		return "board/list";
	}

	@Auth
	@RequestMapping(value = { "/write", "/reply" }, method = RequestMethod.GET)
	public String write(@RequestParam(name = "no", required = true, defaultValue = "0") Long no, Model model) {

		model.addAttribute("vo", boardService.getContents(no));

		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo boardVo,
			@RequestParam(name = "p", required = true, defaultValue = "1") Integer p,
			@RequestParam(name = "kwd", required = false, defaultValue = "") String keyWorld) {

		boardVo.setUserNo(authUser.getNo());
		boardService.addContents(boardVo);

		return "redirect:/board?p=" + p + "&kwd=" + keyWorld;
	}

	@RequestMapping(value = "/view/{no}")
	public String view(@PathVariable("no") Long no, @AuthUser UserVo authUser, Model model) {

		Long user = -1L;
		if (authUser != null) {
			user = authUser.getNo();
		}

		BoardVo boardVo = boardService.getContents(no, user);
		model.addAttribute("vo", boardVo);

		return "board/view";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {

		model.addAttribute("vo", boardService.getContents(no));

		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo boardVo,
			@RequestParam(name = "p", required = true, defaultValue = "1") Integer p,
			@RequestParam(name = "kwd", required = false, defaultValue = "") String keyWorld) {

		boardVo.setNo(no);
		boardService.updateContents(boardVo);
		return "redirect:/board/view/{no}?p=" + p + "&kwd=" + "" + keyWorld;
	}

	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, @AuthUser UserVo authUser,
			@RequestParam(name = "p", required = true, defaultValue = "1") Integer p,
			@RequestParam(name = "kwd", required = false, defaultValue = "") String keyWorld) {

		boardService.deleteContents(no, authUser.getNo());

		return "redirect:/board?p=" + p + "&kwd=" + keyWorld;
	}
}

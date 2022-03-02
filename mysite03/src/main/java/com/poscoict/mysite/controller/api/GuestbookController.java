package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@GetMapping("")
	public JsonResult list(@RequestParam(value = "sn", required = true, defaultValue = "0") Long sn) {

		List<GuestbookVo> list = guestbookService.getMessageList();

		return JsonResult.success(list);
	}

	@PostMapping("")
	public JsonResult post(@RequestBody GuestbookVo guestbookVo) {
		if (guestbookService.addMessage(guestbookVo)) {
			guestbookVo.setPassword("");
			return JsonResult.success(guestbookVo);
		}

		return JsonResult.fail("Adding Guestbook Fail");
	}

	@DeleteMapping("/{no}")
	public JsonResult delete(@PathVariable(name = "no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "") String password) {
		Long deleteNo = 0L;

		if (guestbookService.deleteMessage(no, password)) {
			deleteNo = no;
		} else {
			deleteNo = -1L;
		}

		return JsonResult.success(deleteNo);
	}

}

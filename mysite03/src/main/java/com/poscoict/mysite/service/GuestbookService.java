package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.GuestbookRepository;
import com.poscoict.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}

	public Boolean addMessage(GuestbookVo guestbookVo) {
		return 1 == guestbookRepository.insert(guestbookVo);
	}

	public Boolean deleteMessage(Long no, String password) {
		return 1 == guestbookRepository.delete(no, password);
	}

	public List<GuestbookVo> get3MessageList(Long no) {
		return guestbookRepository.findAllByNo(no);
	}

}

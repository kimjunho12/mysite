package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public boolean addContents(BoardVo boardVo) {
		if (boardVo.getGroupNo() != 0) {
			increaseGroupOrderNo(boardVo);
		}

		return boardRepository.insert(boardVo);
	}

	// 글 수정 / 답글 전
	public BoardVo getContents(Long no) {
		return boardRepository.findByNo(no);
	}

	// view에서 1개의 게시글 가지고오는
	public BoardVo getContents(Long no, Long userNO) {
		boardRepository.updateHit(no);
		return boardRepository.findByNo(no);
	}

	// 글 수정
	public Boolean updateContents(BoardVo boardVo) {
		return boardRepository.update(boardVo);
	}

	// 글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		return boardRepository.delete(no, userNo);
	}
	
	private static final int PAGE_PER_VIEW = 5;
	private static final int BOARD_PER_VIEW = 10;
	// 글 리스트 ( + 검색)
	public Map<String, Object> getContentsList(int currentPage, String keyWorld) {
		
		Map<String, Integer> pager = new HashMap<>();
		int totalCnt = boardRepository.totalCnt(keyWorld);
		int pageCount = (int) Math.ceil((double) totalCnt  / BOARD_PER_VIEW);
		int endPage = (int) (Math.ceil((double) currentPage / PAGE_PER_VIEW)) * PAGE_PER_VIEW;
		int startPage = endPage - PAGE_PER_VIEW + 1;
		int nextPage = (startPage + PAGE_PER_VIEW < pageCount) ? startPage + PAGE_PER_VIEW : startPage;
		int prePage = (startPage - PAGE_PER_VIEW > 0) ? startPage - PAGE_PER_VIEW : startPage;
		
		pager.put("pageCount", pageCount);
		pager.put("currentPage", currentPage);
		pager.put("startPage", startPage);
		pager.put("endPage", endPage < pageCount ? endPage : pageCount);
		pager.put("nextPage", nextPage);
		pager.put("prePage", prePage);
		pager.put("bpv", BOARD_PER_VIEW);
		pager.put("total", totalCnt);

		List<BoardVo> list = boardRepository.findAll(pager, keyWorld);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pager", pager);
		return map;
	}

	private boolean increaseGroupOrderNo(BoardVo boardVo) {
		return false;
	}
}

package com.poscoict.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAll(Map<String, Integer> pager, String kwd) {

		int board_per_view = pager.get("bpv");
		int currentPage = pager.get("currentPage");

		Map<String, Object> param = new HashMap<>();
		param.put("boardStart", (currentPage - 1) * board_per_view);
		param.put("bpv", board_per_view);
		param.put("kwd", kwd);

		return sqlSession.selectList("board.findAll", param);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public int insert(BoardVo boardVo) {
		if (boardVo.getOrderNo() > 0) {
			sqlSession.update("board.updateOrderNo", boardVo);
		}
		return sqlSession.insert("board.insert", boardVo);
	}

	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}

	public int delete(Long no, Long userNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("no", no);
		param.put("userNo", userNo);
		System.out.println("no : " + param.get("no") + " userNo : " + param.get("userNo"));

		return sqlSession.update("board.delete", param);
	}

	public int updateHit(Long no) {
		return sqlSession.update("updateHit", no);
	}

	public int totalCnt(String kwd) {
		return sqlSession.selectOne("board.getTotalCount", kwd);
	}
}

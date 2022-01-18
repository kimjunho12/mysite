package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.GuestbookVo;

public class BoardDao {
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "SELECT " + "b.no, b.title, u.name, b.hit, b.reg_date, u.no " + "FROM " + "	board b "
					+ "		JOIN " + "	user u ON b.user_no = u.no "
//					+ "WHERE b.title LIKE \"%?%\" or b.contents Like \"%?%\" "
					+ "ORDER BY b.g_no DESC , b.o_no ASC";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Long userNo = rs.getLong(6); // 게시글 삭제 시 필요

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("MYSQL 연결 실패");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			Long userNo = vo.getUserNo();
			String title = vo.getTitle();
			String contents = vo.getContents();
			int groupNo = vo.getGroupNo();
			int orderNo = vo.getOrderNo();
			int depth = vo.getDepth();

			String updateSql;
			if (orderNo < 2) {
				updateSql = "UPDATE board SET o_no = o_no + 1 WHERE o_no > ? and g_no > ?";
				pstmt = conn.prepareStatement(updateSql);

				pstmt.setInt(1, orderNo);
				pstmt.setInt(2, groupNo);
				pstmt.executeUpdate();
				pstmt.close();
			}

			// 3. SQL 준비
			String sql = "INSERT INTO board " + "VALUES(null, ?, ?, ?, 0, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, userNo);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);

			pstmt.setString(4, groupNo == 0 ? "(SELECT ifnull(max(g_no), 0) + 1 FROM board)" : String.valueOf(groupNo));
			pstmt.setInt(5, orderNo == 0 ? 1 : orderNo + 1);
			pstmt.setInt(6, depth == 0 && groupNo == 0 ? 0 : depth + 1);

			// 5. SQL 실행
			result = pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("MYSQL 연결 실패");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "webdb";
			String passwd = "webdb";
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			System.out.print("사유 : " + e.getMessage());
		}
		return conn;
	}
}

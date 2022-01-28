package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.poscoict.mysite.vo.BoardVo;

public class BoardDao {
	public List<BoardVo> findAll(Map<String, Integer> pager, String keyWord) {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "SELECT " + "b.no, b.title, u.name, b.hit, b.reg_date, u.no, b.depth, b.state "
					+ "FROM board b JOIN user u ON b.user_no = u.no " + "WHERE b.title LIKE '%" + keyWord
					+ "%' or b.contents Like '%" + keyWord + "%' " + "ORDER BY b.g_no DESC , b.o_no ASC "
					+ "LIMIT ?, ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			int board_per_view = pager.get("bpv");
			int currentPage = pager.get("currentPage");
			pstmt.setInt(1, (currentPage - 1) * board_per_view);
			pstmt.setInt(2, board_per_view);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Long userNo = rs.getLong(6); // 게시글 삭제 시 필요
				int depth = rs.getInt(7); // 답글 표시 시 필요
				String state = rs.getString(8);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setDepth(depth);
				vo.setState(state);

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

	public BoardVo findByNo(Long no) {
		BoardVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "SELECT user_no, title, contents, hit+1, g_no, o_no, depth FROM board b JOIN user u ON b.user_no = u.no WHERE b.no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long userNo = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				int groupNo = rs.getInt(5);
				int orderNo = rs.getInt(6);
				int depth = rs.getInt(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);

				result = vo;
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
			if (orderNo > 0) {
				updateSql = "UPDATE board SET o_no = o_no + 1 WHERE o_no > ? and g_no = ?";
				pstmt = conn.prepareStatement(updateSql);

				pstmt.setInt(1, orderNo);
				pstmt.setInt(2, groupNo);
				pstmt.executeUpdate();
				pstmt.close();
			}

			// 3. SQL 준비
			String sql = "INSERT INTO board VALUES(null, ?, ?, ?, 0, "
					+ (groupNo == 0 ? "(SELECT ifnull(max(b.g_no), 0) + 1 FROM board b)" : String.valueOf(groupNo))
					+ ", ?, ?, now(), 'normal')";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, userNo);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);

			pstmt.setInt(4, orderNo == 0 ? 1 : orderNo + 1);
			pstmt.setInt(5, depth == 0 && groupNo == 0 ? 0 : depth + 1);

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

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			Long no = vo.getNo();
			String title = vo.getTitle();
			String contents = vo.getContents();

			// 3. SQL 준비
			String sql = "UPDATE board SET title=?, contents=? WHERE no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, no);

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

	public boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "UPDATE board SET state = 'deleted' WHERE no = ?;";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);

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

	public boolean updateHit(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "UPDATE board SET hit = hit+1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);

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
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결
			String url = "jdbc:mysql://192.168.0.67:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "webdb";
			String passwd = "webdb";
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			System.out.print("사유 : " + e.getMessage());
		}
		return conn;
	}

	public int totalCnt(String keyWord) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "SELECT COUNT(*) FROM board "
					+ "WHERE title LIKE '%" + keyWord + "%' or contents Like '%" + keyWord + "%'";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
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

}

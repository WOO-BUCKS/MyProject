package com.myweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {
	/*
	 * DAO는 클래스는 단순히 DB연동만 담당하기 때문에 불필요하게 객체를 여러개 생성 할 필요가 없습니다.
	 * 그래서 싱글톤 패턴을 적용해서 객체를 1개만 생성되도록 만듭니다.
	 * 
	 */
	//1. 스스로 객체를 1개 생성합니다.
	private static UserDAO instance = new UserDAO();
	//2. 외부에서 생성자를 호출할 수 없도록 생성자에 private제한을 붙임
	private UserDAO()  {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//커넥션풀을 얻는 방법
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//3. 외부에서 객체생성을 요구하면 getter메서드를 이용해서 1번의 객체를 반환
	public static UserDAO getInstance() {
		return instance;
	}
	
	///////////////////////////////////////////////
	//멤버변수
	private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String uid = "JSP";
	private String upw = "JSP";
	
	private DataSource ds; //데이터베이스 연결풀을 저장해놓는 객체
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//아이디 중복검사
	public int checkId(String id){
		int result = 0;
		
		String sql = "select * from users where id = ?";
		
		try {
			//conn = DriverManager.getConnection(url,uid,upw);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//close작업을 클래스로 생성해놓고 호출.
				JdbcUtil.close(conn, pstmt, rs);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
	
	//회원가입 메서드
	public int join(UserVO vo) {
		int result = 0;
		String sql = "insert into users(id,pw,name,email,address) values(?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.close(conn, pstmt, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
	//로그인 메서드
	public UserVO login(String id,String pw) {
		UserVO vo =null;
		String sql = "select id, name from users where id=? and pw=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return vo;
	}
	//회원 정보 가져오기 메서드
	public UserVO getInfo(String id) {
		UserVO vo = null;
		
		String sql = "select * from users where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setPw(null);
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.close(conn, pstmt, rs);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return vo;
	}
	//회원 정보 수정 메서드
	public int update(UserVO vo) {
		int result = 0;
		
		String sql = "update users set "
									+ "pw=?,name=?,email=?,address=? "
									+ "where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.close(conn, pstmt, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
	//회원 정보 삭제 메서드
	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from users where id = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.close(conn, pstmt, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return result;
	}
}



















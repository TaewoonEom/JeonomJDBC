package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩이 있어야 함.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "JEONOMJDBC";
	private final String PASSWORD = "JEONOMJDBC";
	
	public Member selectOne(String memberId) {
		Member member = null;
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		try {
			Class.forName(DRIVER_NAME);
			Connection conn
					= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				// rsetToMember
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PW"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getNString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setRegDate(rset.getDate("REG_DATE"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		// 1. 왜 mList에 member가 들어가나요?
		// -> mList 객체에는 Member타입만 들어가기 때문에 member 객체를 생성해서 넣을 수 있다.
		// 2. rset을 mList에는 왜 못 넣는건가요?
		// -> add 메소드에는 Member 타입만 들어가는데 rset은 ResultSet 타입이기 떄문에
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3.2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>();
		try {
			// 1.
			Class.forName(DRIVER_NAME);
			// 2.
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3.
			Statement stmt = conn.createStatement();
			// 4. / 5.
			String query = "SELECT * FROM MEMBER_TBL";
			ResultSet rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) {
				// rsetToMember
				Member member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PW"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getNString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setRegDate(rset.getDate("REG_DATE"));
				mList.add(member);
//				System.out.println("이름 : " + rset.getString("MEMBER_NAME")); // 컬럼명 그대로 오타없이 작성
//				System.out.println("아이디 : " + rset.getString("MEMBER_ID")); // 컬럼명 그대로 오타없이 작성
//				System.out.println("이메일 : " + rset.getString("EMAIL"));	   // 컬럼명 그대로 오타없이 작성
//				System.out.println("나이 : " + rset.getInt("AGE"));	   		   // 컬럼명 그대로 오타없이 작성
//				System.out.println("등록일 : " + rset.getDate("REG_DATE"));	   // 컬럼명 그대로 오타없이 작성
			}
			// 6.
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 * 
		 */
			// 1.
		try {
			Class.forName(DRIVER_NAME);
			// 2.
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3.
			Statement stmt = conn.createStatement();
			// 4.
			String query = "INSERT INTO MEMBER_TBL VALUES('" + member.getMemberId() + "', '" + member.getMemberPw() + "', '" + member.getMemberName() + "', '"
						+ member.getGender() + "', '" + member.getAge() + "', '" + member.getEmail() + "', '" + member.getPhone() + "', '" 
						+ member.getAddress() +"', '" + member.getHobby() + "', DEFAULT)";
			// ResultSet rset = stmt.executeQuery(query); // SELECT 할때만 ResultSet은 Select의 결과
			int result = stmt.executeUpdate(query); 	  // DML의 경우 호출하는 메소드(INSERT, UPDATE, DELETE)
														  // ★ 시험문제로 많이 나오는 부분 ★
			// 후처리
			if(result > 0 ) {
				// 성공 메시지 출력
				System.out.println("데이터 등록 성공!");
				// commit;
			}else {
				// 실패 메시지 출력
				System.out.println("데이터 등록 실패!");
				// rollback;
			}
			// 6.
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

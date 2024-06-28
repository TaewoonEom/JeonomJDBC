package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	
	// (3) DAO 생성 후 생성자에서 초기화
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public void insertMember(Member member) {
		mDao.insertMember(member);
	}
	
	// View에서 memberId값 받아야 하니까 removeMember(String memberId)
	// return 하는 값의 자료형이 int니까 void 대신 int
	public int removeMember(String memberId) {
		// DML의 결과는 int니까 int result
		// memberId 전달해야 되니까 deleteMember(memberId)
		int result = mDao.deleteMember(memberId);
		// 호출한 곳에서 써야되니까 result 리턴
		return result;
	}

	public void listMember() {
		
	}

	public List<Member> printAllMember() {
		// 여러개니까 List, 멤버니까 List<Member>
		List<Member> mList = mDao.selectList();
		// 호출한 곳에서 써야되니까 mList 리턴, MemberView : 34
		return mList;
	}
	
	// View가 준것 받아야 되니까 printOneMember(String memberId)
	public Member printOneMember(String memberId) {
		// 한개니까 List없어도 됨, Member
		Member member = mDao.selectOne(memberId);
		// 호출한 곳에서 써야되니까 member 리턴, MemberView : 44
		return member;
	}

	public int modifyMember(Member modifyInfo) {
		// DML의 결과는 int니까 int result
		// modifyInfo 전달해야 되니까 updateMember(modifyInfo)
		int result = mDao.updateMember(modifyInfo);
		// 호출한 곳에서 써야 되니까 return result, MemberView : 65
		return result;
	}
	
}

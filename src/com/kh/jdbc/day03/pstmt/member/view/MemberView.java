package com.kh.jdbc.day03.pstmt.member.view;

import java.util.Scanner;

import com.kh.jdbc.day03.pstmt.member.controller.MemberController;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberView {
	MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}
	
	public void startProgram() {
		finish :
		while(true) {
			int choice = this.mainMenu();
			switch(choice) {
				case 1 : // <회원가입>
					// 1. 회원정보를 입력받은 후
					// 입력받은 정보를 객체에 저장한 후
					Member member = this.inputInfo();
					// 2. 객체를 컨트롤러에 전달
					int result = mController.registerMember(member);
					if(result > 0) {
						printMsg("회원가입 성공!");
					}else {
						printMsg("회원가입 실패!");
					}
					break;
				case 2 : // <로그인>
					member = this.inputLoginInfo();
					// 입력한 ID와 PW이 DB에 있는가?
					member = mController.checkLogin(member);
					if(member != null) {
						this.printOneMember(member);
					}else {
						printMsg("존재하지 않는 정보입니다.");
					}
					break;
				case 3 : // <회원정보 수정>
					String memberId = inputMemberId();
					member = mController.checkMember(memberId);
					if(member != null) {
						member = modifyMember();
						member.setMemberId(memberId);
						result = mController.updateMember(member);
						if(result > 0) {
							printMsg("수정 성공!");
						}else {
							printMsg("수정 실패!");
						}
					}else {
						printMsg("존재하지 않는 정보입니다.");
					}
					break;
				case 4 : // <회원탈퇴>
					memberId = inputMemberId();
					member = mController.checkMember(memberId);
					if(member != null) {
						result = mController.removeMember(memberId);
						if(result > 0) {
							printMsg("삭제 성공!");
						}
					}else{
						printMsg("존재하지 않는 정보입니다.");
					}
					break;
				case 5 : break;
				case 6 : break;
				case 9 : 
					printMsg("프로그램 종료");
					break finish;
			}
		}
	}

	private Member modifyMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 정보 수정 ======");
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member(memberPw, email, phone, address, hobby);
		return member;
	}

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 아이디 입력 ======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		return memberId;
	}

	private void printOneMember(Member member) {
		System.out.println("====== 회원 정보 출력 ======");
		System.out.printf("이름 : %s, 아이디 : %s, 나이 : %d, 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s, 취미 : %s, 가입날짜 : %s\n"
				, member.getMemberName(), member.getMemberId(), member.getAge(), member.getGender(), member.getEmail()
				, member.getPhone(), member.getAddress(), member.getHobby(), member.getRegDate());
	}

	private Member inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 로그인 정보 입력 ======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		return member;
	}

	private Member inputInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		// 리턴 여러개 안되니까 객체 이용
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, address, hobby);
		// 호출한 곳에서 사용하니까 return member;
		return member;
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원정보 수정");
		System.out.println("4. 회원탈퇴");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		// 호출하는 곳에서 쓰니까 return input;
		return input;
	}

	private void printMsg(String msg) {
		System.out.println("[결과] : " + msg);
	}

}

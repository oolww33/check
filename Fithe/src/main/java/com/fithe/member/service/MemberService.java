package com.fithe.member.service;


import java.util.List;

import com.fithe.member.vo.MemberVO;

public interface MemberService {
	//회원가입
	public int memberInsert(MemberVO mvo);
	
	//로그인
	public MemberVO memberLogin(MemberVO mvo);
	
	//아이디 중복확인
	public MemberVO memberidcheck(String mid);
	
	//마이 페이지 정보 읽어오기
//	public MemberVO memberPage(String mnum);
	public List<MemberVO> memberPage(MemberVO mvo);
	
	//회원 수정
	public int memberUpdate(MemberVO mvo);
	
	//회원 삭제
	public int memberDelete(MemberVO mvo);
	
//	//비밀번호 변경
//	public int memberPwChange(MemberVO mvo);
	
	//아이디 찾기
	public MemberVO memberIdFind(MemberVO mvo);
	
	//비밀번호 찾기
	public MemberVO memberPwFind(MemberVO mvo);
}

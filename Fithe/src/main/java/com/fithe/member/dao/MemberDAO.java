package com.fithe.member.dao;


import java.util.List;

import com.fithe.member.vo.MemberVO;

public interface MemberDAO {
	//회원가입
	public int memberInsert(MemberVO mvo);
	
	//로그인
	public MemberVO memberLogin(MemberVO mvo); 
	
	//아이디 중복 확인
	public MemberVO memberidcheck(String mid);
	
	//마이 페이지 정보 읽어오기
//	public MemberVO memberPage(String mnum);
	public List<MemberVO> memberPage(MemberVO mvo);
	
	//회원 수정
	public int memberUpdate(MemberVO mvo);
	
	//회원 삭제
	public int memberDelete(MemberVO mvo);
}

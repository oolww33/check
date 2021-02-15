package com.fithe.member.dao;


import com.fithe.member.vo.MemberVO;

public interface MemberDAO {
	//회원가입
	public int memberInsert(MemberVO mvo);
	//로그인
	public MemberVO memberLogin(MemberVO mvo); 
	//아이디 중복 확인
	public MemberVO memberidcheck(String mid);
}

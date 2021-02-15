package com.fithe.member.service;


import com.fithe.member.vo.MemberVO;

public interface MemberService {
	//회원가입
	public int memberInsert(MemberVO mvo);
	//로그인
	public MemberVO memberLogin(MemberVO mvo);
	//아이디 중복확인
	public MemberVO memberidcheck(String mid);
}

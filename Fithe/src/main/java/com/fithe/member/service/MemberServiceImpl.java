package com.fithe.member.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithe.member.dao.MemberDAO;
import com.fithe.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	private Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	private MemberDAO memberDAO;
	
	//Autowired어노테이션을 생성자에 작성하여 의존성 주입
	@Autowired(required=false)
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//회원입력
	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberInsert함수진입");
		int nCnt = memberDAO.memberInsert(mvo);
		return nCnt;
	}
	
	//로그인
	@Override
	public MemberVO memberLogin(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberLofin함수진입");
		MemberVO mvo1 = memberDAO.memberLogin(mvo);
		return mvo1;
	}
	
	//아이디중복체크
	@Override
	public MemberVO memberidcheck(String mid) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberidcheck함수진입");
		return memberDAO.memberidcheck(mid);
	}

//	@Override
//	public MemberVO memberPage(String mnum) {
//		// TODO Auto-generated method stub
//		logger.info("MemberServiceImpl memberPage함수진입");
//		return memberDAO.memberPage(mnum);
//	}
	
	//마이페이지
	@Override
	public List<MemberVO> memberPage(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberPage함수진입");
		return memberDAO.memberPage(mvo);
	}
	
	//회원 수정
	@Override
	public int memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberUpdate함수진입");
		return memberDAO.memberUpdate(mvo);
	}

	@Override
	public int memberDelete(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberDelete함수진입");
		return memberDAO.memberDelete(mvo);
	}

	@Override
	public MemberVO memberIdFind(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberIdFind함수진입");
		return memberDAO.memberIdFind(mvo);
	}

	@Override
	public MemberVO memberPwFind(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberPwFind함수진입");
		return memberDAO.memberPwFind(mvo);
	}

//	@Override
//	public int memberPwChange(MemberVO mvo) {
//		// TODO Auto-generated method stub
//		logger.info("MemberServiceImpl memberPwChange함수진입");
//		return memberDAO.memberPwChange(mvo);
//	}
}

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
	
	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberInsert함수진입");
		int nCnt = memberDAO.memberInsert(mvo);
		return nCnt;
	}

	@Override
	public MemberVO memberLogin(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberLofin함수진입");
		MemberVO mvo1 = memberDAO.memberLogin(mvo);
		return mvo1;
	}

	@Override
	public MemberVO memberidcheck(String mid) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberidcheck함수진입");
		return memberDAO.memberidcheck(mid);
	}

}
package com.fithe.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithe.common.dao.ChabunDAO;
import com.fithe.member.vo.MemberVO;

@Service
@Transactional
public class ChabunServiceImpl implements ChabunService {
	
	private ChabunDAO chabunDAO;
	
	//의존성 주입
	@Autowired(required=false)
	public ChabunServiceImpl(ChabunDAO chabunDAO) {
		this.chabunDAO = chabunDAO;
	}
	
	@Override
	public MemberVO getChabun() {
		// TODO Auto-generated method stub
		MemberVO mvo = chabunDAO.getChabun();
		return mvo;
	}

}

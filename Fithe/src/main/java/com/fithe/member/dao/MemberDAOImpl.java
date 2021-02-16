package com.fithe.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fithe.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private Logger logger = Logger.getLogger(MemberDAOImpl.class);
	
	//Autowired어노테이션으로 의존성 주입
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		return (Integer)sqlSession.insert("memberInsert", mvo);
	}

	@Override
	public MemberVO memberLogin(MemberVO mvo) {
		// TODO Auto-generated method stub
		return (MemberVO)sqlSession.selectOne("memberLogin", mvo);
	}

	@Override
	public MemberVO memberidcheck(String mid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberidcheck", mid);
	}

//	@Override
//	public MemberVO memberPage(String mnum) {
//		// TODO Auto-generated method stub
//		return (MemberVO)sqlSession.selectOne("memberPage", mnum);
//	}
	
	@Override
	public List<MemberVO> memberPage(MemberVO mvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("memberPage", mvo);
	}

	@Override
	public int memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		return (Integer)sqlSession.update("memberUpdate", mvo);
	}

	@Override
	public int memberDelete(MemberVO mvo) {
		// TODO Auto-generated method stub
		return (Integer)sqlSession.update("memberDelete", mvo);
	}

}

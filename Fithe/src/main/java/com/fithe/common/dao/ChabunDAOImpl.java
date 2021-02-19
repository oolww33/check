package com.fithe.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fithe.common.vo.ScheduleVO;
import com.fithe.member.vo.MemberVO;

@Repository
public class ChabunDAOImpl implements ChabunDAO {
	
	//의존성 주입
	@Autowired(required=false)
	private SqlSession sqlSession; 
	
	@Override
	public MemberVO getChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getChabun");
	}


}

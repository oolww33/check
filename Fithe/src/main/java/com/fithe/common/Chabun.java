package com.fithe.common;

public abstract class Chabun {
	// 회원번호에 채번(아이디별 고유번호)를 부여한다.
	public static final String CHABUN_M = "M";  // MEMBER
	public static final String CHABUN_S = "S";  // SCHEDULE
	
	public static String chabunSetting(String cnumber) {
		
		for(int i=cnumber.length(); i<4; i++) {
			cnumber = "0" + cnumber;
		}
		return cnumber;
	}
	
	//회원번호
	public static String memberChabun(String mnumber) {
		return CHABUN_M.concat(Chabun.chabunSetting(mnumber));
	}
	
	//스케줄번호
	public static String scheduleChabun(String mnumber) {
		return CHABUN_S.concat(Chabun.chabunSetting(mnumber));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package com.fithe.schedule.vo;

public class ScheduleVO {
	private String snum;
	private String sdate;
	private String smemo;
	private String smemo1;
	private String smemo2;
	private String smemo3;
	private String smemo4;
	private String mid;
	
	public ScheduleVO() {
		
	}
	
	public ScheduleVO(String mid,String snum,String sdate, String smemo, String smemo1, String smemo2, String smemo3, String smemo4) {
		this.snum = snum;
		this.sdate = sdate;
		this.smemo = smemo;
		this.smemo1 = smemo1;
		this.smemo2 = smemo2;
		this.smemo3 = smemo3;
		this.smemo4 = smemo4;
		this.mid = mid;
	}

	public String getSnum() {
		return snum;
	}

	public void setSnum(String snum) {
		this.snum = snum;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getSmemo() {
		return smemo;
	}

	public void setSmemo(String smemo) {
		this.smemo = smemo;
	}

	public String getSmemo1() {
		return smemo1;
	}

	public void setSmemo1(String smemo1) {
		this.smemo1 = smemo1;
	}

	public String getSmemo2() {
		return smemo2;
	}

	public void setSmemo2(String smemo2) {
		this.smemo2 = smemo2;
	}

	public String getSmemo3() {
		return smemo3;
	}

	public void setSmemo3(String smemo3) {
		this.smemo3 = smemo3;
	}

	public String getSmemo4() {
		return smemo4;
	}

	public void setSmemo4(String smemo4) {
		this.smemo4 = smemo4;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}

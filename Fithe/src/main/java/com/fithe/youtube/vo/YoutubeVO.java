package com.fithe.youtube.vo;

public class YoutubeVO {
	private String ynum;
	private String ylink;
	private String yid;
	private String ydelyn;
	
	public YoutubeVO() {
		
	}
	
	public YoutubeVO(String ynum, String ylink, String yid, String ydelyn) {
		this.ynum = ynum;
		this.ylink = ylink;
		this.yid = yid;
		this.ydelyn = ydelyn;
	}

	public String getYnum() {
		return ynum;
	}

	public void setYnum(String ynum) {
		this.ynum = ynum;
	}

	public String getYlink() {
		return ylink;
	}

	public void setYlink(String ylink) {
		this.ylink = ylink;
	}

	public String getYid() {
		return yid;
	}

	public void setYid(String yid) {
		this.yid = yid;
	}

	public String getYdelyn() {
		return ydelyn;
	}

	public void setYdelyn(String ydelyn) {
		this.ydelyn = ydelyn;
	}
	
}

package com.fithe.member.vo;

public class MemberVO {
	
	private String mnum;
	private String mid;
	private String mpw;
	private String mname;
	private String mbir;
	private String mzonecode;
	private String maddress_road;
	private String maddress_detail;
	private String mph;
	private String memail;
	private String minsertdate;
	private String mupdatedate;
	private String mdelyn;
	private String mauth;
	private String mgender;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String mnum, String mid, String mpw, String mname, String mbir, String mzonecode,
			String maddress_road, String maddress_detail, String mph, String memail, String minsertdate,
			String mupdatedate, String mdelyn, String mauth, String mgender) {
		this.mnum = mnum;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mbir = mbir;
		this.mzonecode = mzonecode;
		this.maddress_road = maddress_road;
		this.maddress_detail = maddress_detail;
		this.mph = mph;
		this.memail = memail;
		this.minsertdate = minsertdate;
		this.mupdatedate = mupdatedate;
		this.mdelyn = mdelyn;
		this.mauth = mauth;
		this.mgender = mgender;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMbir() {
		return mbir;
	}

	public void setMbir(String mbir) {
		this.mbir = mbir;
	}

	public String getMzonecode() {
		return mzonecode;
	}

	public void setMzonecode(String mzonecode) {
		this.mzonecode = mzonecode;
	}

	public String getMaddress_road() {
		return maddress_road;
	}

	public void setMaddress_road(String maddress_road) {
		this.maddress_road = maddress_road;
	}

	public String getMaddress_detail() {
		return maddress_detail;
	}

	public void setMaddress_detail(String maddress_detail) {
		this.maddress_detail = maddress_detail;
	}

	public String getMph() {
		return mph;
	}

	public void setMph(String mph) {
		this.mph = mph;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMinsertdate() {
		return minsertdate;
	}

	public void setMinsertdate(String minsertdate) {
		this.minsertdate = minsertdate;
	}

	public String getMupdatedate() {
		return mupdatedate;
	}

	public void setMupdatedate(String mupdatedate) {
		this.mupdatedate = mupdatedate;
	}

	public String getMdelyn() {
		return mdelyn;
	}

	public void setMdelyn(String mdelyn) {
		this.mdelyn = mdelyn;
	}

	public String getMauth() {
		return mauth;
	}

	public void setMauth(String mauth) {
		this.mauth = mauth;
	}

	public String getMgender() {
		return mgender;
	}

	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
}

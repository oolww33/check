package com.fithe.member.controller;


import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fithe.common.Chabun;
import com.fithe.common.service.ChabunService;
import com.fithe.member.service.MemberService;
import com.fithe.member.vo.MemberVO;

@Controller
public class MemberController {

	private Logger logger = Logger.getLogger(MemberController.class);
	
	private JavaMailSenderImpl mailSender;
	private PasswordEncoder passwordEncoder;
	private MemberService memberService;
	private ChabunService chabunService;
	
	//Autowired 어노테이션으로 의존성 주입
	@Autowired(required=false)
	public MemberController ( MemberService memberService, ChabunService chabunService
			, PasswordEncoder passwordEncoder, JavaMailSenderImpl mailSender) {
		this.memberService = memberService;
		this.chabunService = chabunService;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}
	
	// login 페이지로 이동만을 하기 때문에 method는 GET으로 하였다.
	@RequestMapping(value="loginform", method=RequestMethod.GET)
	public String memberLoginForm() {
		logger.info("controller login함수");
		return "/login/loginform";
	}
	
	// 회원가입 페이지 이동만 하기  때문에 GET방식 사용
	@RequestMapping(value="insertForm", method=RequestMethod.GET)
	public String memberInsertForm() {
		logger.info("controller memberInsertForm함수");
		return "/member/insertform";
	}
	
	// 회원가입 페이지에서 입력 받은 값들을 DB에 저장
	// 저장이 완료되면 login창으로 
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String memberInsert(MemberVO mvo, HttpServletRequest request) {
		logger.info("Controller memberInsert함수 진입");
		//채번
		String mnum = Chabun.memberChabun(chabunService.getChabun().getMnum());
		
		
		//비밀번호 암호화
		logger.info("암호화 전의 비밀번호 : " + request.getParameter("mpw"));
		String mpw = passwordEncoder.encode(request.getParameter("mpw"));
		logger.info("암호화된 비밀번호 : " + mpw);
		
		//생년월일
		String mbiry = request.getParameter("mbiry");
		String mbirm = request.getParameter("mbirm");
		String mbird = request.getParameter("mbird");
		String mbir = mbiry+mbirm+mbird;
		
		//핸드폰 
		String mph1 = request.getParameter("mph1");
		String mph2 = request.getParameter("mph2");
		String mph3 = request.getParameter("mph3");
		String mph = mph1+"-"+mph2+"-"+mph3;
		
		//이메일
		String memail1 = request.getParameter("memail1");
		String memail2 = request.getParameter("memail2");
		String memail = memail1+"@"+memail2;
		
		mvo.setMnum(mnum);
		mvo.setMid(request.getParameter("mid"));
		mvo.setMpw(mpw);
		mvo.setMname(request.getParameter("mname"));
		mvo.setMbir(mbir);
		mvo.setMgender(request.getParameter("mgender"));
		mvo.setMzonecode(request.getParameter("mzonecode"));
		mvo.setMaddress_road(request.getParameter("maddress_road"));
		mvo.setMaddress_detail(request.getParameter("maddress_detail"));
		mvo.setMph(mph);
		mvo.setMemail(memail);
		
		logger.info(mvo.getMnum());
		logger.info(mvo.getMid());
		logger.info(mvo.getMpw());
		logger.info(mvo.getMname());
		logger.info(mvo.getMbir());
		logger.info(mvo.getMgender());
		logger.info(mvo.getMzonecode());
		logger.info(mvo.getMaddress_road());
		logger.info(mvo.getMaddress_detail());
		logger.info(mvo.getMph());
		logger.info(mvo.getMemail());
		
		int nCnt = memberService.memberInsert(mvo);
		logger.info(nCnt);
		if(nCnt == 1) {
			
			return "/member/insertOK";
		}else {
			
			return "/member/insertform";
		}
	}
	
	// 아이디가 입력되지 않았을때 에러발생 해결해야함.
	// 로그인버튼을눌렀을시(세션처리)
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, MemberVO mvo, Model model) {
		logger.info("Controller memberlogin 함수진입");
		String mpw = request.getParameter("mpw");
		logger.info("mpw : " + mpw);
		
		MemberVO mvo1 = memberService.memberLogin(mvo);
		logger.info(mvo1.getMnum());
		if(mvo1.getMid() != null || mvo1.getMid() != "") {
			// 암호화된 비밀번호를 비교하여 참일경우 로그인 진행
			boolean bool = passwordEncoder.matches(mpw, mvo1.getMpw());
			logger.info("bool : " + bool);
			
			if(bool) {
				logger.info("로그인 성공");
				HttpSession session = request.getSession(); //세션부여
				session.setAttribute("mnum", mvo1.getMnum()); 
				session.setAttribute("mid", mvo1.getMid());
				session.setMaxInactiveInterval(-1);//세션 무한대
				
				return "/login/loginOK";
				
			}else{
				logger.info("로그인 실패");
				
				return "/login/loginX";
			}
		}else {
			return "/login/loginX";
		}
	}
	
	//로그아웃
	//완료
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request, HttpSession session) {
		logger.info("Controller memberlogout 함수진입");
		session.invalidate(); //세션을 삭제
		return "/login/logout";
	}
	
	//아이디 중복체크
	//ResponseBody어노테이션을 사용해야 ajax 형식의 데이터를 주고받을 수 있어서 사용했다.
	//HttpServletRequest으로 mid의 값을 받아와서 비교해서 mid와 같은 값이 있는지 확인하는 작업
	@ResponseBody
	@RequestMapping(value="idCheck", method=RequestMethod.POST)
	public int idCheck(HttpServletRequest request){
		logger.info("Controller idCheck 함수 진입");
		
		String mid = request.getParameter("mid");
		MemberVO mvo = memberService.memberidcheck(mid);
		int result = 0;
		if(mvo != null) {
			result = 1;
		}
		return result;
	}
	
//	@RequestMapping(value="/mail")
//	public String mailSending(HttpServletRequest request) {
//		String setform = "oolww11@gmail.com";
//		String tomail = request.getParameter("tomail");
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		
//		try {
//			  MimeMessage message = mailSender.createMimeMessage();
//			  MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			  messageHelper.setFrom(setform);  // 보내는사람 생략하거나 하면 정상작동을 안함
//			  messageHelper.setTo(tomail);     // 받는사람 이메일
//			  messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
//			  messageHelper.setText(content);  // 메일 내용
//			
//			  mailSender.send(message);
//
//			} catch(Exception e){
//			  System.out.println(e);
//			}
//		return "email";
//	}
	
	// 마이페이지에서 자신의 정보 불러오기
	@RequestMapping(value="mypage", method=RequestMethod.POST)
	public String memberPage(MemberVO mvo, HttpServletRequest request, Model model) {
		logger.info("Controller memberPage 함수 진입");
		//로그인된 상태에서의 (mnum의)세션값을 가져온다
		HttpSession session = request.getSession();
		logger.info(session.getAttribute("mnum"));
		String mnum = (String)session.getAttribute("mnum");

		//로그인된 세션의 mnum값으로 회원의 정보불러오기 위한 작업
		mvo.setMnum(mnum);
		
		List<MemberVO> listM = memberService.memberPage(mvo);
		int nCnt = listM.size();
		logger.info("nCnt : " + nCnt);
		if(nCnt == 1) {
			model.addAttribute("listM", listM);
			return "/member/mypage";
		}else {
			
			return "/login/loginX";
		}
	}
	
	//회원 수정
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String memberUpdate(MemberVO mvo, HttpServletRequest request, Model model) {
		logger.info("Controller memberUpdate 함수 진입");
		//핸드폰 
		String mph1 = request.getParameter("mph1");
		String mph2 = request.getParameter("mph2");
		String mph3 = request.getParameter("mph3");
		String mph = mph1+"-"+mph2+"-"+mph3;
				
		//이메일
		String memail1 = request.getParameter("memail1");
		String memail2 = request.getParameter("memail2");
		String memail = memail1+"@"+memail2;
		
		mvo.setMph(mph);
		mvo.setMemail(memail);
		
		logger.info(mvo.getMnum());
		logger.info(mvo.getMid());
		logger.info(mvo.getMzonecode());
		logger.info(mvo.getMaddress_road());
		logger.info(mvo.getMaddress_detail());
		logger.info(mvo.getMph());
		logger.info(mvo.getMemail());
		
		
		int nCnt = memberService.memberUpdate(mvo);
		logger.info(nCnt);
		
		logger.info(mvo.getMzonecode());
		logger.info(mvo.getMaddress_road());
		logger.info(mvo.getMaddress_detail());
		logger.info(mvo.getMph());
		logger.info(mvo.getMemail());
		if(nCnt == 1) {
			
			HttpSession session = request.getSession();
			logger.info(session.getAttribute("mnum"));
			String mnum = (String)session.getAttribute("mnum");
			
			List<MemberVO> listM = memberService.memberPage(mvo);
			int nCnt1 = listM.size();
			logger.info("nCnt : " + nCnt);
			
			if(nCnt1 == 1) {
				model.addAttribute("listM", listM);
				return "/member/updateOK";
			}
		}	
		return "/member/mypage";
	}
	
	//회원 탈퇴
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String memberDelete(MemberVO mvo, HttpServletRequest request) {
		logger.info("Controller memberDelete 함수 진입");
		logger.info(mvo.getMnum());
		logger.info(mvo.getMid());
		logger.info(mvo.getMzonecode());
		logger.info(mvo.getMaddress_road());
		logger.info(mvo.getMaddress_detail());
		logger.info(mvo.getMph());
		logger.info(mvo.getMemail());
		
		int nCnt = memberService.memberDelete(mvo);
		logger.info(nCnt);
		
		if(nCnt == 1) {
			HttpSession session = request.getSession();
			logger.info(session.getAttribute("mnum"));
			String mnum = (String)session.getAttribute("mnum");
			session.invalidate();
			return "/login/loginform";
		}
		return "/member/mypage";
	}
}


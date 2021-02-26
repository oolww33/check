package com.fithe.member.controller;


import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
import com.fithe.schedule.service.ScheduleService;
import com.fithe.schedule.vo.ScheduleVO;

@Controller
public class MemberController {

	private Logger logger = Logger.getLogger(MemberController.class);
	
	private PasswordEncoder passwordEncoder;
	private MemberService memberService;
	private ChabunService chabunService;
	private ScheduleService scheduleService;
	
	//Autowired 어노테이션으로 의존성 주입
	@Autowired(required=false)
	public MemberController ( MemberService memberService, ChabunService chabunService
			, PasswordEncoder passwordEncoder,ScheduleService scheduleService) {
		this.memberService = memberService;
		this.chabunService = chabunService;
		this.passwordEncoder = passwordEncoder;
		this.scheduleService = scheduleService;
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
		mvo.setMpw(mpw);
		mvo.setMbir(mbir);
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
		
		MemberVO mvo1 = memberService.memberLogin(mvo);
		logger.info(mvo1.getMid());
		if(mvo1.getMid() != null || mvo1.getMid() != "") {
			HttpSession session = request.getSession(); //세션부여
			// 암호화된 비밀번호를 비교하여 참일경우 로그인 진행
			boolean bool = passwordEncoder.matches(mpw, mvo1.getMpw());
			logger.info("bool : " + bool);
			
			if(bool) {
				logger.info("로그인 성공");
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
	
	
	
	// 마이페이지에서 자신의 정보 불러오기
	@RequestMapping(value="myinfo", method=RequestMethod.POST)
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
			logger.info("session : " + session.getAttribute("mnum"));
			
			List<MemberVO> listM = memberService.memberPage(mvo);
			int nCnt1 = listM.size();
			logger.info("nCnt : " + nCnt);
			
			if(nCnt1 == 1) {
				model.addAttribute("message", "수정 하시겠습니까?");
				model.addAttribute("url", "mypage.fit");
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
			session.invalidate();
			return "/login/loginform";
		}
		return "/member/mypage";
	}
	

	// 이메일보내기
//	@RequestMapping(value="/mail")
//	public String mailSending(HttpServletRequest request) {
//		String setform = "oolww11@gmail.com";
//		String tomail = "oolww33@naver.com";
//		String title = "test";
//		String content = "test";
//		final MimeMessagePreparator preparator = new MimeMessagePreparator() { 		
//			@Override 
//			public void prepare(MimeMessage mimeMessage) throws Exception { 
//				
//				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//					helper.setFrom(mailSender.getUsername()); 
//					helper.setTo(tomail); 
//					helper.setSubject(title); 
//					helper.setText(content); 
//				} 
//			}; 
//			
//			mailSender.send(preparator); 
//			
//			return "/login/loginform";
//	}
//	
	//아이디찾기 창으로이동
	@RequestMapping(value="idfind", method=RequestMethod.GET)
	public String idFind() {
		logger.info("Controller idFind 함수 진입");
		return "/find/idfind";
	}
	
	//아이디찾기
	@RequestMapping(value="idfindOK", method=RequestMethod.POST)
	public String idFindOK(HttpServletRequest request, MemberVO mvo) {
		logger.info("Controller idFindOK 함수 진입");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		logger.info("이름 : " + mname);
		logger.info("이메일 : " + memail);
		
		MemberVO mvo1 = memberService.memberIdFind(mvo);
		logger.info(mvo1);
		
		if(mvo1 != null) {
			logger.info(mvo1.getMid());
			return "/find/idfindOK";
		}else {
			return "/find/idfindX";
		}
		
	}
	
	//비밀번호 찾기 창으로이동
	@RequestMapping(value="pwfind", method=RequestMethod.GET)
	public String pwFind() {
		logger.info("Controller pwFind 함수 진입");
		return "/find/pwfind";	
	}
	
	//비밀번호 찾기
	@RequestMapping(value="pwfindOK", method=RequestMethod.POST)
	public String pwFindOK(HttpServletRequest request, MemberVO mvo) {
		logger.info("Controller pwFindOK 함수 진입");
		String mid = request.getParameter("mid");
		String memail = request.getParameter("memail");
		
		logger.info("아이디 : " + mid);
		logger.info("이메일 : " + memail);
		
		
		return "/find/pwfindOK";
	}
	
//	@RequestMapping(value="schedulePopup", method=RequestMethod.GET)
//	public String popup() {
//		return "/member/schedulePopup";
//	}
//	
//	//스케줄 입력
//	//ajax 통신으로 ResponseBody 어노티에션 사용 
//	@ResponseBody
//	@RequestMapping(value="scheduleinsert", method=RequestMethod.POST)
//	public String scheduleInsert(HttpServletRequest request, ScheduleVO svo) {
//		HttpSession session = request.getSession();
//		String mid = (String)session.getAttribute("mid");
//		logger.info("Controller scheduleInsert 함수 진입");
//		String sdate = request.getParameter("sdate");
//		String smemo = request.getParameter("smemo");
//		String smemo1 = request.getParameter("smemo1");
//		String smemo2 = request.getParameter("smemo2");
//		String smemo3 = request.getParameter("smemo3");
//		String smemo4 = request.getParameter("smemo4");
//		logger.info(sdate);
//		logger.info(smemo);
//		logger.info(smemo1);
//		logger.info(smemo2);
//		logger.info(smemo3);
//		logger.info(smemo4);
//		logger.info(mid);
//		svo.setMid(mid);
//		int nCnt = scheduleService.scheduleInsert(svo);
//		logger.info(nCnt);
//		if(nCnt == 1) {
//			return "G";
//		}else {
//			return "B";
//		}		
//	}
	

	
	// 마이페이지로 페이지이동
	@RequestMapping(value="mypage", method= {RequestMethod.GET, RequestMethod.POST})
	public String memberInfo(Model model, ScheduleVO svo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("mid");
		logger.info(mid);
		List<ScheduleVO> list = scheduleService.scheduleSelect(mid);
		logger.info(list.size());
		model.addAttribute("list", list);
		return "/member/meminfo";
			
	}

	
}



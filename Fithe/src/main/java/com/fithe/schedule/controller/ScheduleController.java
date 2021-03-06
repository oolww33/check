package com.fithe.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fithe.member.controller.MemberController;
import com.fithe.schedule.service.ScheduleService;
import com.fithe.schedule.vo.ScheduleVO;

@Controller
public class ScheduleController {
	
	private Logger logger = Logger.getLogger(ScheduleController.class);
	
	private ScheduleService scheduleService;
	
	@Autowired(required=false)
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@RequestMapping(value="schedulePopup", method=RequestMethod.GET)
	public String popup() {
		return "/member/schedulePopup";
	}
	
	//스케줄 입력
	//ajax 통신으로 ResponseBody 어노티에션 사용 
	@ResponseBody
	@RequestMapping(value="scheduleinsert", method=RequestMethod.POST)
	public String scheduleInsert(HttpServletRequest request, ScheduleVO svo) {
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("mid");
		logger.info("Controller scheduleInsert 함수 진입");
		String sdate = request.getParameter("sdate");
		String smemo = request.getParameter("smemo");
		String smemo1 = request.getParameter("smemo1");
		String smemo2 = request.getParameter("smemo2");
		String smemo3 = request.getParameter("smemo3");
		String smemo4 = request.getParameter("smemo4");
		logger.info(sdate);
		logger.info(smemo);
		logger.info(smemo1);
		logger.info(smemo2);
		logger.info(smemo3);
		logger.info(smemo4);
		logger.info(mid);
		svo.setMid(mid);
		int nCnt = scheduleService.scheduleInsert(svo);
		logger.info(nCnt);
		if(nCnt == 1) {
			return "G";
		}else {
			return "B";
		}		
	}
}

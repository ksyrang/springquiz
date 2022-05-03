package com.care.quiz;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.quiz.membership.service.MemberServiceImpl;

//쉐러링 완료
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired HttpSession session;
	@Autowired private MemberServiceImpl memberService;
	/*
	 * http://localhost:8085/quiz/
	 */
	@RequestMapping(value = "/")//최초에 페이지 진입 시 다른 URL이 아니라 /만으로 메인 페이지로 진입하도록 설정
	public String index(Model model) {
		logger.info("Welcome home! The client locale is {}.");
		model.addAttribute("formpath", "home");//최초 진입시 formpath는 null이기 떄문에 무한 루프로 빠질 수 있어 상수 home 입력 
		return "index"; //index로 이동
	}
	//http://localhost:8085/quiz/index?formpath=?
	@RequestMapping("index")//top의 네비 버튼에 따라 들어온 formpath의 값을 다시 c:import url에 입력하여 index에서 가운데에 표시할 내용으로 이동
	public void index(Model model, String formpath) {
		
		model.addAttribute("formpath", formpath);
	}		
	@RequestMapping("home")
	public void home() {	}
	

	@RequestMapping("login")
	public String login() {
		return "member/loginForm";
	}	
	@RequestMapping("member")
	public String member() {
		return "member/memberForm";
	}	
	@RequestMapping("board")
	public String board() {
		return "board/boardForm";
	}	
	@RequestMapping("memberList") //회원 목록
	public String memberList(Model model) {
//		model.addAttribute("memberlist",memberService.memberList());
		return "member/memberlistForm";
	}	
	@RequestMapping("userinfo") //회원 정보
	public String userinfo(Model model) {
		return "member/userInfoForm";
	}
	@RequestMapping("modifyCheck") //회원정보 수정
	public String modifyCheck(Model model) {
		return "member/modifyCheckForm";
	}	
	@RequestMapping("memberDelete")//탈퇴
	public String memberDelete(Model model) {
		return "member/memberDeleteForm";
	}
}

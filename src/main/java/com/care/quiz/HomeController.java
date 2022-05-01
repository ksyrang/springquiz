package com.care.quiz;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//쉐러링 완료
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
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
	@GetMapping("home")
	public void home() {	}
	

	@GetMapping("login")
	public String login() {
		return "member/loginForm";
	}	
	@GetMapping("member")
	public String member() {
		return "member/memberForm";
	}	
	@GetMapping("board")
	public String board() {
		return "board/boardForm";
	}	
}

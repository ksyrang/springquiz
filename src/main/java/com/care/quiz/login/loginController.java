package com.care.quiz.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.quiz.login.service.LoginServiceImpl;
import com.care.quiz.membership.dto.LoginDTO;

@Controller
public class loginController {
	
	@Autowired HttpSession session;
	@Autowired private LoginServiceImpl loginSvc;
	
	@PostMapping("loginProc")
	public String loginProc(LoginDTO loginData, Model model) {
//		System.out.println("로그인 프록 진입");
		int result = loginSvc.checkMember(loginData);
//		System.out.println("로그인 검사 결과 : "+result);
		switch (result) {
		case 1:
			return "redirect:index?formpath=home";
		case 2:
			model.addAttribute("msg","로그인 정보를 입력하세요.");
			return "forward:index?formpath=login";//return "로그인 정보를 입력하세요.";
		case 3:
			model.addAttribute("msg","미등록 회원입니다.");
			return "forward:index?formpath=login";//return "미등록 회원입니다.";
		case 4:
			model.addAttribute("msg","비밀번호가 다릅니다.");
			return "forward:index?formpath=login";//return "비밀번호가 다릅니다.";
			
		default:
			model.addAttribute("msg","관리자에게 문의해주세요.");
			return "forward:index?formpath=login";//return "관리자에게 문의해주세요.";
		
		}
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:index?formpath=home";
	}
	
	

}
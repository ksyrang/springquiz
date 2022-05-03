package com.care.quiz.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.quiz.login.dao.ILoginDAO;
import com.care.quiz.membership.dto.LoginDTO;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired HttpSession session;
	@Autowired private ILoginDAO loginDAO;
	
	@Override
	public int checkMember(LoginDTO member) {
		if(member == null|| member.getId() == null|| member.getPw() == null) return 2;//"로그인 정보를 입력하세요.";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		LoginDTO check = loginDAO.selectMember(member.getId());
		if(check.getId() == null) return 3;//"미등록 회원입니다.";
		if(!encoder.matches(member.getPw(), check.getPw())) return 4;//"비밀번호가 다릅니다.";
		
		session.setAttribute("id", check.getId());
		return 1;//"환영합니다.";
	}
	

}

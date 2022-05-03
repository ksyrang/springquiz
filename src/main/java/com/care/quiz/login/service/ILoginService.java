package com.care.quiz.login.service;

import com.care.quiz.membership.dto.LoginDTO;

public interface ILoginService {
	
	public int checkMember(LoginDTO member);
	
}

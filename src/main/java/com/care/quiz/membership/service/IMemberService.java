package com.care.quiz.membership.service;

import com.care.quiz.membership.dto.PostDTO;
import com.care.quiz.membership.dto.MemberDTO;


public interface IMemberService {
	
	public String IsExistId(String id);
	public int memberProc(MemberDTO member, PostDTO post);
	public String sendAuth(String email);
	public String checkAuth(String authNum);
	
}//service class end

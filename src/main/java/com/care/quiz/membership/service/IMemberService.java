package com.care.quiz.membership.service;

import org.springframework.stereotype.Service;

import com.care.quiz.membership.dto.PostDTO;
import com.care.quiz.membership.dto.MemberDTO;


public interface IMemberService {
	
	public boolean IsExistId(String id);
	public int memberProc(MemberDTO member, PostDTO post);
	public void sendAuth(String email);
	public String authComfirm(String authNumber);
	
}//service class end

package com.care.quiz.login.dao;

import org.springframework.stereotype.Repository;

import com.care.quiz.membership.dto.LoginDTO;

@Repository
public interface ILoginDAO {
	
	public LoginDTO selectMember(String id);

}

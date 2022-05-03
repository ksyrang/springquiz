package com.care.quiz.membership.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.quiz.membership.dto.AllDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.dto.PostDTO;

@Repository
public interface IMemberDAO {
	
	public String IsExistId(String id);

	public int insertLogin(LoginDTO login);
	public int insertMember(MemberDTO member);
	public int insertPost(PostDTO post);
	
	
	public ArrayList<AllDTO> selectAll();
	public ArrayList<PostDTO> selectpostAll();
	public ArrayList<MemberDTO> selectmemberAll();

	public MemberDTO selectmemberInfo(String id);
	public PostDTO selectpostInfo(String id);


}

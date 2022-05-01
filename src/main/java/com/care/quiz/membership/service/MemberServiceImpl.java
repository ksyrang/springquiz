package com.care.quiz.membership.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.quiz.membership.dao.IMemberDAO;
import com.care.quiz.membership.dto.PostDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;

@Service
public class MemberServiceImpl implements IMemberService  {

	@Autowired HttpSession session;
	@Autowired IMemberDAO memberDAO;
	
	@Override
	public boolean IsExistId(String id) {
		String idcheck = memberDAO.IsExistId(id);
		//주의사항 객체에 null일 경우 조건 확인 시 객체 자체의 null부터 확인하고 isempty를 확인할껏 메소드 연산에 의해 nullpoint 예외 발생
		if(idcheck == null || idcheck.isEmpty()) return true;  //사용 가능
		else {
			return false; //중복된 아이디
		}
	}
	@Override
	public int memberProc(MemberDTO member, PostDTO post) {
		//아이디 중복 확인 여부
		if(session.getAttribute("idcheck") == null) return 2; // 아이디 중복 확인을 안함
		boolean idcheck = (boolean)session.getAttribute("idcheck");
		if(!idcheck) return 2;// 아이디 중복됨
		else if(member.getPw() == null||member.getPwOk()==null||
				member.getPw().isEmpty()||member.getPwOk().isEmpty()) return 3; //비밀번호 미입력
		else if(!member.getPw().equals(member.getPwOk())) return 4;//비밀번호 비교
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String securePw = encoder.encode(member.getPw());//입력된 정보를 암호화한다.
		member.setPw(securePw);
		LoginDTO login = member;	
		
		
		int insertLogin_result = memberDAO.insertLogin(login);
		if(insertLogin_result != 1) return 12;// 로그인 정보 등록 이상
		int insertMem_result = memberDAO.insertMember(member);
		if(insertMem_result != 1) return 13;// 멤버 정보 등록 이상
		int insertPost_result = memberDAO.insertPost(post);
		if(insertPost_result != 1)  return 14;// 주소 정보 등록 이상
		return 1;//등록 완료
	}
	@Override
	public void sendAuth(String email) {
		
	}
	@Override
	public String authComfirm(String authNumber) {
		return null;
	}	

}//MemberServiceImpl class end

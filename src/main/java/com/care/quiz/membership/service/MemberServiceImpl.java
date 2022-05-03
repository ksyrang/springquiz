package com.care.quiz.membership.service;

import java.util.ArrayList;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.quiz.membership.dao.IMemberDAO;
import com.care.quiz.membership.dto.PostDTO;
import com.care.quiz.membership.dto.AllDTO;
import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;

@Service
public class MemberServiceImpl implements IMemberService  {

	@Autowired HttpSession session;
	@Autowired IMemberDAO memberDAO;
	@Autowired JavaMailSender mailSender;
	
	@Override
	public String IsExistId(String id) {
		if(id ==null) return "아이디를 입력 하세요.";
		
		//int idcheck = memberDAO.IsExistId(id);
		String idcheck = memberDAO.IsExistId(id);
		if(idcheck == null || idcheck.isEmpty()) {
			session.setAttribute("idcheck", true);
			return "사용 가능한 아이디 입니다.";
		}
		else {
			session.setAttribute("idcheck", false);
			return "중복된 아이디입니다.";
		}				
	}
	
	@Override
	public String sendAuth(String email) {
		if(email == null) return "이메일을 입력해 주세요.";
		MimeMessage message = mailSender.createMimeMessage();//
		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(message, true, "UTF-8");			
			
			Random random = new Random();//랜덤클래스 개체 인스턴스
			String number = String.format("%06d", random.nextInt(1000000));
			session.setAttribute("authNum", number);
			
			msgHelper.setSubject("이메일 인증"); //이메일 제목
			msgHelper.setText(number); //이메일 내요
			msgHelper.setTo(email); //이메일 수신자			
			
			mailSender.send(message);//메일을 송부	
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "송신완료";
	}
	@Override
	public String checkAuth(String authNum) {
		if(authNum == null) return "인증 번호를 입력하세요.";
		if(authNum.equals(session.getAttribute("authNum"))) {
			session.setAttribute("checkAuth", true);
			return "인증번호를 확인 하였습니다.";			
		}else {
			session.setAttribute("checkAuth", false);
			return "인증번호가 틀립니다.";
		}
	}	
	
	
	@Override
	public int memberProc(MemberDTO member, PostDTO post) {
		//아이디 중복 확인
//		System.out.println("proc_idcheck : "+ session.getAttribute("idcheck"));
		if(session.getAttribute("idcheck") == null||!(boolean)session.getAttribute("idcheck")) {
			session.invalidate();//확인 후 불필요 세션 삭제
			return 2; // 아이디 중복 확인을 안함
		}
		//비밀번호 비교
		if(member.getPw() == null||member.getPwOk()==null||
				member.getPw().isEmpty()||member.getPwOk().isEmpty()) return 3; //비밀번호 미입력
		else if(!member.getPw().equals(member.getPwOk())) return 4;//비밀번호 비교
		//이메일 인증 확인
		if(session.getAttribute("checkAuth") == null||!(boolean)session.getAttribute("checkAuth")) {
			session.invalidate();//확인 후 불필요 세션 삭제
			return 5;
		}
		
		
		//비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String securePw = encoder.encode(member.getPw());//입력된 정보를 암호화한다.
		member.setPw(securePw);
//		System.out.println("securePw : " +securePw);
		LoginDTO login = member;	
		
		session.invalidate();//확인 후 불필요 세션 삭제
		
		//DB 저장
		int insertLogin_result = memberDAO.insertLogin(login);
		if(insertLogin_result != 1) return 12;// 로그인 정보 등록 이상
		int insertMem_result = memberDAO.insertMember(member);
		if(insertMem_result != 1) return 13;// 멤버 정보 등록 이상
		int insertPost_result = memberDAO.insertPost(post);
		if(insertPost_result != 1)  return 14;// 주소 정보 등록 이상
		return 1;//등록 완료
	}
	
	@Override
	public ArrayList<MemberDTO> memberList() {
		ArrayList<MemberDTO> list = memberDAO.selectmemberAll();
		return list;
	}

	public AllDTO userinfo(String id) {
		AllDTO user = new AllDTO();
		MemberDTO member = memberDAO.selectmemberInfo(id);
		PostDTO post = memberDAO.selectpostInfo(id);
		if(member != null) {
			user.setId(member.getId());
			user.setEmail(member.getEmail());
			user.setGender(member.getGender());
		}
		if(post != null) {
			user.setZipcode(post.getZipcode());
			user.setAddr1(post.getAddr1());
			user.setAddr2(post.getAddr2());
		}
			
		return user;
	}

	

}//MemberServiceImpl class end

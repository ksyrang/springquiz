package com.care.quiz.membership;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.quiz.membership.dto.LoginDTO;
import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.dto.PostDTO;
import com.care.quiz.membership.service.MemberServiceImpl;


@Controller
public class MemberController {
	@Autowired HttpSession session;
	@Autowired MemberServiceImpl memberService;
	
	
	@ResponseBody
	@PostMapping(value="idcheck", produces = "text/html; charset=UTF-8")
	public String idCheck(@RequestBody (required = false)String id) {
		System.out.println("Post method idCheck");
		if(id == null||id.isEmpty()){
			return "아이디를 입력하세요";
		}else {
			if(memberService.IsExistId(id)) {
				session.setAttribute("idcheck", true);
				return "등록 가능";
			}else {
				session.setAttribute("idcheck", false);
				return "등록된 아이디";
			}
		}
	}
	
	@PostMapping("insert")
	public String insert(MemberDTO member,PostDTO post, Model model) {
		System.out.println("insert processing");
		session.invalidate();
		int result = memberService.memberProc(member, post);
		System.out.println("memberProc line pass");
		System.out.println("memberProc result : " + result);
		
		switch (result) {
			case 1: //등록 성공
				model.addAttribute("formpath", "login");
				return "index";
			case 2://아이디 중복 미확인
				model.addAttribute("msg", "아이디 중복 여부를 확인하세요");
				break;
			case 3://비밀번호 미입력
				model.addAttribute("msg", "비밀번호 미입력");
				break;
			case 4://비밀번호 불일치
				model.addAttribute("msg", "비밀번호 불일치");
				break;
			case 12://로그인 정보 등록 이상
				model.addAttribute("msg", "로그인 정보 등록 이상");
				break;
			case 13://멤버정보 등록 이상
				model.addAttribute("msg", "멤버정보 등록 이상");
				break;
			case 14://주소정보 등록 이상
				model.addAttribute("msg", "주소정보 등록 이상");
				break;
			default: // 특이사항 발생
				model.addAttribute("msg", "특이사항 발생");
				break;	
		}
		return "forward:index?formpath=member";
		
	}
	
	
}

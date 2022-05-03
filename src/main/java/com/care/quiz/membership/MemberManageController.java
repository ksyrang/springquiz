package com.care.quiz.membership;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.quiz.membership.dto.MemberDTO;
import com.care.quiz.membership.service.MemberServiceImpl;

@Controller
public class MemberManageController {

	@Autowired HttpSession session;
	@Autowired private MemberServiceImpl memberService;
	
	@RequestMapping("memberListProc")
	public String memberListProc(Model model) {
		ArrayList<MemberDTO> list = memberService.memberList();
		model.addAttribute("list", list);	
		return "forward:index?formpath=memberList";
	}
	
	@RequestMapping("userInfoProc")
	public String userInfoProc(String id , Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(id==null||id==""||sessionId==null||sessionId=="") {
			return "forward:memberListProc";
		}
		if(sessionId.equals(id) ||"admin".equals(model)) {
			model.addAttribute("user",memberService.userinfo(id));
			return "forward:index?formpath=userinfo";
			
		}
		return "forward:memberListProc";
	}
	
	
}

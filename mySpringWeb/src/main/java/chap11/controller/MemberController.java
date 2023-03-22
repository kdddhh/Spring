package chap11.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import chap11.model.*;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@PostMapping("/member/loginProcess")
	public String login(LoginCommand command, HttpSession session) {
		String view = "";
		
		if(memberService.checkLoginAuth(command)) {
			Member member = memberService.findMember(command.getEmail());
			command.setName(member.getName());
			command.setPassword("");
			session.setAttribute("auth", command);
			view = "redirect:/main";
		}
		else {
			session.setAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
			view = "redirect:/member/login";
		}
		
		return view;
	}
	
	@PostMapping("/member/findMemberProcess")
	public String findMember(@RequestParam(value="email") String email, Model model) {
		Member member = memberService.findMember(email);
		
		if(member != null) {
			model.addAttribute("member", member);
		}
		else {
			model.addAttribute("memberFindMsg", "이메일을 다시 확인해 주세요!");
		}
		
		return "member/findMember";
	}
	
	@PostMapping("/member/changePasswdProcess")
	public String changePasswd(ChangePasswdCommand command, Model model) {
		String view = "";
		
		try {
			changePasswordService.changePassword(command);
			view = "redirect:/member/logout";
		}
		catch(Exception e) {
			model.addAttribute("changePasswdMsg", "올바른 비밀번호를 입력해주세요.");
			view = "member/changePasswd";
		}
		
		return view;
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		String view = "";
		
		if(session.getAttribute("auth") != null) {
			session.invalidate();
			view = "redirect:/member/login";
		}
		else {
			view = "redirect:/main";
		}
		
		return view;
	}
	
	@GetMapping("/member/allFindMember")
	public String allFind(HttpSession session) {
		String result = "";
		
		ArrayList<Member> list = memberService.allfindMember();
		for(Member find : list) {
			result += "<tr><td>"+ find.getEmail() + "</td>" +
					"<td>" + find.getName() + "</td>" + "<td>" + find.getRegisterDateTime().toLocalDate() + "</td>" +"</tr>";
		}
		
		session.setAttribute("table", result);
		
		return "member/allFindMember";
	}
	
}
	

	
	
	
	
	
	
	
	
	
	
	

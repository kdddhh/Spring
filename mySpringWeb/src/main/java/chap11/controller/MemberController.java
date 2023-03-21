package chap11.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import chap11.model.LoginCommand;
import chap11.model.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/login")
	public String loginGet() {
		return "member/login";
	}
	
	@PostMapping("/member/login")
	public String loginPost(LoginCommand login, HttpSession session) {
		String view = "";
		
		if(memberService.checkLoginAuth(login)) {
			session.setAttribute("auth", login);
			view = "redirect:/main";
		}
		else {
			session.setAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
			view = "redirect:/member/login";
		}
		
		return view;
	}
	
}

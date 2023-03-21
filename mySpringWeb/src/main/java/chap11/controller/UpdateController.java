package chap11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chap11.exception.WrongIdPasswordException;
import chap11.model.ChangePasswordService;

@Controller
public class UpdateController {
	
	private ChangePasswordService cps;
	
	public void setCps(ChangePasswordService cps) {
		this.cps = cps;
	}
	
	@RequestMapping("/update/step1")
	 public String handleStep1() {
	      return "update/step1";
	   }
	
	 @PostMapping("/update/step1")
	   public String handleStep2(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password, @RequestParam(value = "confirmPassword")String confirmPassword, Model model) {
	      String view = "update/step1";
	      
	      try {
	          cps.changePassword(email, password, confirmPassword);
	       }
	       catch (WrongIdPasswordException e) {
	    	   model.addAttribute("msg", "중복된 이메일 주소가 발견되었습니다.");
	       }
	       
	       
	       return view;
	   }
	
}

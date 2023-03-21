package chap11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chap11.exception.DuplicateMemberException;
import chap11.model.*;

@Controller
public class RegisterController {
   
   private MemberRegisterService memberRegisterService;
   
   public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
      this.memberRegisterService = memberRegisterService;
   }
   
   @RequestMapping("/register/step1") // http://localhost:8080/mySpringWeb/register/step1
   public String handleStep1() {
      return "register/step1";
   }
   
   @PostMapping("/register/step2")
   public String handleStep2(@RequestParam(value="agree", defaultValue="false") boolean agree, Model model) {
      String view = "";
      
      if(agree) {
         view = "register/step2";
         model.addAttribute("formData", new RegisterRequest());
      }
      else {
         view = "redirect:/register/step1";
      }
      
      return view;
   }
   
   @GetMapping("/register/step2")
   public String handleStep2Get() {
      return "redirect:/register/step1";
   }
   
   /*
   서버에서 사용자 입력 검증
   @PostMapping("/register/step3")
   public String handleStep3(RegisterRequest regReq) {
      String view = "register/step2";
      
      if(regReq.getName().length() >= 2 && regReq.getPassword().length() >= 4 &&
            regReq.getPassword().equals(regReq.getConfirmPassword())) {
         try {
            memberRegisterService.regist(regReq);
            view = "register/step3";
         }
         catch (DuplicateMemberException e) {
         }
      }
      String view = "register/step3";
      
      try {
         memberRegisterService.regist(regReq);
      }
      catch (DuplicateMemberException e) {
         view = "register/step2";
      }
      
      
      return view;
   }
   */
   
   @PostMapping("/register/step3")
   public String handleStep3(@ModelAttribute("formData") RegisterRequest regReq, Model model) {
      String view = "register/step3";
      
      try {
         memberRegisterService.regist(regReq);
      }
      catch (DuplicateMemberException e) {
         model.addAttribute("msg", "중복된 이메일 주소가 발견되었습니다.");
         view = "register/step2";
      }
      
      
      return view;
   }
   
   @GetMapping("/main")
   public String handleMain() {
      return "main";
   }
}
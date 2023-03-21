package chap11.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap11.controller.MemberController;
import chap11.controller.RegisterController;
import chap11.controller.UpdateController;
import chap11.model.ChangePasswordService;
import chap11.model.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@Bean
	public RegisterController registerController() {
		RegisterController rc = new RegisterController();
		rc.setMemberRegisterService(memberRegisterService);
		
		return rc;
	}
	
	@Bean
	public UpdateController updateController() {
		UpdateController uc = new UpdateController();
		uc.setCps(changePasswordService);
		
		return uc;
	}
	
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
}

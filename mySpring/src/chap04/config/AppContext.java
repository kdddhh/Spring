package chap04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04.model.*;

@Configuration
public class AppContext {
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		// pwdSvc.setMemberDAO(this.memberDAO());
		
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrint() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter memberListPrint() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		/*
		 * memberInfoPrinter.setMemberDAO(this.memberDAO());
		 * memberInfoPrinter.setPrinter(memberPrint());
		 */
		
		return memberInfoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		
		return versionPrinter;
	}
}

package chap04.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Qualifier;
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
	
	/* @Bean => 기본 방식
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	} */
	
	/* => 빈 이름으로 식별하는 방법
	 * @Bean 
	 * public MemberPrinter memberPrint1() { 
	 * 	return new MemberPrinter(); 
	 * }
	 * 
	 * @Bean 
	 * public MemberPrinter memberPrint2() { 
	 * 	return new MemberPrinter(); 
	 * }
	 */
	
	/*
	 @Bean 
	 @Qualifier("printerQ") // => 한정자를 통해 또 하나의 이름을 선언. 빈의 이름보다 한정자 명이 우선한다. 
	 public MemberPrinter memberPrint1() { 
		 return new MemberPrinter(); 
	 }
	 */
	
	@Bean
	@Qualifier("printerP")
	public MemberPrinter memberPrint1() { 
	 return new MemberPrinter(); 
	}
	 
	 @Bean
	 @Qualifier("printerC")
	 public MemberSummaryPrinter memberPrint2() { 
	 	return new MemberSummaryPrinter(); 
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
	
	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		// 사용자가 원하는 형식의 시간값 출력 폼을 설정함
	}
}

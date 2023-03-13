package chap03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap03.model.*;

@Configuration
public class AppConf1 {
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO();
	}
	
	@Bean
	public MemberPrinter memberPrint() {
		return new MemberPrinter();
	}
}

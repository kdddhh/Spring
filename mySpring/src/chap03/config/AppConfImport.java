package chap03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import chap03.model.*;

@Configuration
@Import(AppConf2.class) // 나중에 동작한다.
public class AppConfImport { // 먼저 동작하고
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}

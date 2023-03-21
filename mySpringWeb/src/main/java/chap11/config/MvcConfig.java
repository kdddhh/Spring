package chap11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override // 뷰 이름만 반환하는 경우 일괄 등록하는 메소드
	public void addViewControllers(ViewControllerRegistry registry) {
		// http://localhost:8080/mySpringWeb/main
		registry.addViewController("/main").setViewName("main");
		
		// http://localhost:8080/mySpringWeb/register/step1
		registry.addViewController("/register/step1").setViewName("register/step1");
		
		// http://localhost:8080/mySpringWeb/member/login
		// registry.addViewController("/member/login").setViewName("member/login");
		
	}
}

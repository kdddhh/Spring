package chap09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
		// name이라는 매개변수는 @RequestParam를 통해서 name이라는 파라미터가 있다면 그 값을 매개변수 name으로 저장하고,
		// required가 false이기 때문에 꼭 필요하진 않다.
		// Model은 스프링에서 사용하는 저장소
		
		model.addAttribute("greeting", "안녕하세요..." + name);
		
		return "hello"; // jsp문서명(view의 이름)
	}
	/*
	 * get방식으로 http://localhost:8080/mySpringWeb에 /hello로 요청이 들어오면 해당 메소드를 실행해라는 의미
	 */
}

package chap09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

//	get 방식의 요청에만 응답한다. (모든 요청에 응답 : RequestMapping)
	@GetMapping("/hello") // http://localhost:8080/mySpringWeb/hello?name=손흥민
	public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
//	요청에 name이라는 파라미터가 있다면 그 name 파라미터의 값을 String name으로 저장해주고, 없어도 상관 없다(required = false)
//	Model -> 스프링 웹 MVC에서 사용하는 저장소
		model.addAttribute("greeting", "안녕하세요 ... " + name);
		return "hello";
//		-> view의 이름
	}
}

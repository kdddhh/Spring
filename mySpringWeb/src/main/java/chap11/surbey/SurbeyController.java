package chap11.surbey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/surbey") 
public class SurbeyController {
	
	@GetMapping //http://localhost:8080/mySpringWeb/surbey
	public String form() {
		return "surbey/surbeyForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "surbey/submitted";
	}
	
}

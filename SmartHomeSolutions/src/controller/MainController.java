package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/signup")
	public ModelAndView registration() {

		String message1 = "Registration:";
		return new ModelAndView("signup", "message", message1);
	}
	
	@RequestMapping("/login")
	public ModelAndView signin()
	{
		String message2 = "Log In";
		return new ModelAndView("login", "message", message2);
	}
	

}


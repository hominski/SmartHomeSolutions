package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/registration")
	public ModelAndView registration() {

		String message1 = "Registration:";
		return new ModelAndView("registration", "message", message1);
	}
	
	@RequestMapping("/signin")
	public ModelAndView signin()
	{
		String message2 = "Signin";
		return new ModelAndView("signin", "message", message2);
	}
	

}


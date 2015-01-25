package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/registration")
	public ModelAndView helloWorld() {

		String message = "Registration:";
		System.out.println(message);
		return new ModelAndView("registration", "message", message);
	}

}


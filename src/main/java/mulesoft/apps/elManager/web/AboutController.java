package mulesoft.apps.elManager.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView about(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about");
		return mav;
	}
	
}

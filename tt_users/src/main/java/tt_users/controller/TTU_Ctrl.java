package tt_users.controller;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class TTU_Ctrl  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8858170491066167861L;
	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		
		
		return model;
	}
	
	@RequestMapping(value = {"/error404"} , method = RequestMethod.GET)
	public ModelAndView  err404(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		
		return model;
	}


}

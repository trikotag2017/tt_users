package tt_users.controller;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
            ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        mav.addObject("datetime", new Date());
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session) throws Exception 
	{
		ModelAndView model = new ModelAndView("index");
		//if(true)
			//throw new Exception();
		
		return model;
	}
	
	@RequestMapping(value = {"/error404"} , method = RequestMethod.GET)
	public ModelAndView  err404(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		
		return model;
	}


}

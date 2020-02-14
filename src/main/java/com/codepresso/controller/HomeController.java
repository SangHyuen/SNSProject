package com.codepresso.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.service.MemberServiceimpl;
import com.codepresso.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	

	@Autowired
	MemberServiceimpl memberService;

	@Autowired
	PostService postService;

	@Autowired
	MemberVO memberVO;

	@Autowired
	PostVO postVO;
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
/*	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "";
	}*/
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
		
	}@RequestMapping(value = "/signup")
	public ModelAndView signup(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		return mav;
	}

	@RequestMapping(value = "/post/detail/{postId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable ("postId") int postID){

		ModelAndView mav = new ModelAndView();
		mav.addObject("id",postID);
		mav.setViewName("detail");
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView postMy(@CookieValue(value = "accesstoken",required = false) String cookieToken){
		MemberVO memVO = new MemberVO();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		if(cookieToken != null) {
		mav.addObject("user",memVO);
		}
		return mav;
	}


}

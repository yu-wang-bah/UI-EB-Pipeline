package com.bah.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	
	@RequestMapping(value={"/scientists/**", "/programmers/**", "/photos/**"})
	public ModelAndView index(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "error_description", required = false) String errorDescription,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("/index.html");

			
		return mav;
	}
	
}

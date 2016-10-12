package com.hmsy.prpwc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HimpController {
	
	@Controller
	public class HomeController {
		
		@RequestMapping("/himps")
		public String welcome(Map<String, Object> model) {
			model.put("message", "Hello Spring Boot with Tiles");
			return "welcome";
		}

	}

}

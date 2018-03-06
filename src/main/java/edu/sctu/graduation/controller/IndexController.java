package edu.sctu.graduation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@RequestMapping
	@ResponseBody
	public String index(HttpServletRequest req) {
		return "hello,world";

	}
}

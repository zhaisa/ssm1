package com.jenkins.ssm1.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jenkins.ssm1.domain.User;
import com.jenkins.ssm1.service.IUserService;

@Controller
public class LoginController {
	@Autowired
    private IUserService userservice;
	@RequestMapping(value="/login", produces = "text/html;charset=UTF-8")
	public ModelAndView toLogin() throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String login(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password, Map<String, Object> map,
			HttpSession session) {
		if ((username != null && username != "") && (password != null && password != "")) {
			User user = userservice.findByUsername(username);
			if (user != null) {
				session.setAttribute("user", user);
				return "redirect index";
			} else {
				map.put("msg", "用户名或者密码错误");
				return "err";
			}
		} else {
			map.put("msg", "缺少必要参数");
			return "err";
		}
	}

}

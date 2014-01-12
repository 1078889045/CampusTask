package com.niit.controller;

import java.io.IOException;
import java.util.List;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.StudentsDAO;
import com.niit.model.*;
import com.niit.service.UsersService;

@Controller
public class UsersController {

	@Autowired 
	private UsersService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView("redirect:/views/Login.jsp");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		Integer studentNo = Integer.valueOf(request.getParameter("studentNo"));
		StudentsDAO sdao = new StudentsDAO();
		Students students = sdao.findById(studentNo);
		String contact = request.getParameter("contact");
		Integer userLevel = 0;
		Users user = new Users();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setStudents(students);
		user.setContact(contact);
		user.setUserLevel(userLevel);
	   service.add(user);
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		List<Users> user = service.dao.findByUserName(userName);
		String userPassword = request.getParameter("userPassword");
		if(service.login(userName, userPassword)){
			mav.setViewName("/success");
			mav.addObject("success","登录成功！进入个人主页...");
			mav.addObject("user", user.get(0));
		}else{
			mav.setViewName("/error");
			mav.addObject("error","密码错误！");
		}
		
		return mav;
	}
}

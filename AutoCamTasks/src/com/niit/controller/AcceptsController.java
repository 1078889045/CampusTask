package com.niit.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.*;
import com.niit.model.*;
import com.niit.service.AcceptsService;

@Controller
public class AcceptsController {

	@Autowired 
	private AcceptsService service;
	
	@RequestMapping(value = "/request/{uid}.htm", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int uid) throws ParseException,
			IllegalStateException, IOException{

		ModelAndView mav = new ModelAndView("/success2");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		if (uid > 0) {
			System.out.println(uid);
		} else {
			System.out.println(uid + "---不对---");
		}
		int tid = Integer.parseInt(request.getParameter("taskId"));
		TasksDAO tdao = new TasksDAO();
		Tasks task = tdao.findById(tid);
		UsersDAO udao = new UsersDAO();
		Users user = udao.findById(uid);
		Double offer = Double.parseDouble(request.getParameter("offer"));
		String explain  = request.getParameter("explain");
		Accepts ac = new Accepts();
		ac.setTasks(task);
		ac.setUsers(user);
		ac.setOffer(offer);
		ac.setExplain(explain);
		service.add(ac);
		mav.addObject("success","提交请求成功！重新进入个人主页...");
		mav.addObject("user",user);
		return mav;
	}
	
	@RequestMapping(value = "/single/{tid}.htm", method = RequestMethod.GET)
	public ModelAndView single(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int tid) throws ParseException,
			IllegalStateException, IOException{

		ModelAndView mav = new ModelAndView("/single");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		if (tid > 0) {
			System.out.println(tid);
		} else {
			System.out.println(tid + "---不对---");
		}
		TasksDAO tdao = new TasksDAO();
		Tasks ta = tdao.findById(tid);
		List acceptList = service.getList();
		mav.addObject("task",ta);
		mav.addObject("acceptList",acceptList);
		return mav;
	}
	
	@RequestMapping(value = "/choose/{aid}.htm", method = RequestMethod.GET)
	public ModelAndView choose(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int aid) throws ParseException,
			IllegalStateException, IOException{

		ModelAndView mav = new ModelAndView("/success2");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		if (aid > 0) {
			System.out.println(aid);
		} else {
			System.out.println(aid + "---不对---");
		}
		Accepts ac = service.get(aid);
		
		Tasks ta = new Tasks();
		ta.setTaskId(ac.getTasks().getTaskId());
		ta.setUsers(ac.getTasks().getUsers());
		ta.setTaskContent(ac.getTasks().getTaskContent());
		ta.setReward(ac.getTasks().getReward());
		ta.setReleaseTime(ac.getTasks().getReleaseTime());
		ta.setStopTime(ac.getTasks().getStopTime());
		ta.setAcceptId(ac.getUsers().getUserId());
		ta.setIfComplete(false);
		TasksDAO tdao = new TasksDAO();
		tdao.merge(ta);
		mav.addObject("success","选择成功！重新进入个人主页...");
		mav.addObject("user",ac.getTasks().getUsers());
		return mav;
	}
}

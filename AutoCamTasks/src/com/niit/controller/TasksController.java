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
import com.niit.service.TasksService;

@Controller
public class TasksController {

	@Autowired 
	private TasksService service;
	
	@RequestMapping(value = "/main")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("/MainPage");
		List tasksList = service.getList();
		System.out.println(tasksList);
		mav.addObject("Tasks", tasksList);
		return mav;
	}
	
	
	@RequestMapping(value = "/delete/{tid}.htm", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int tid) {

		ModelAndView mav = new ModelAndView("/success2");
		if (tid > 0) {
			System.out.println(tid);
		} else {
			System.out.println(tid + "---不对---");
		}
		Tasks task = service.get(tid);
		service.delete(task);
		mav.addObject("success","取消任务成功！重新进入个人主页...");
		mav.addObject("user", task.getUsers());
		return mav;
	}
	
	@RequestMapping(value = "/finish/{tid}.htm", method = RequestMethod.GET)
	public ModelAndView finish(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int tid) {

		ModelAndView mav = new ModelAndView("/success2");
		if (tid > 0) {
			System.out.println(tid);
		} else {
			System.out.println(tid + "---不对---");
		}
		Tasks task = service.get(tid);
		Boolean ifComplete = true;
		Tasks ta = new Tasks();
		ta.setTaskId(tid);
		ta.setUsers(task.getUsers());
		ta.setTaskContent(task.getTaskContent());
		ta.setReward(task.getReward());
		ta.setReleaseTime(task.getReleaseTime());
		ta.setStopTime(task.getStopTime());
		ta.setAcceptId(task.getAcceptId());
		ta.setIfComplete(ifComplete);
		service.update(ta);
		
		if (task.getAcceptId() == null) {
			
		} else {
			UsersDAO udao = new UsersDAO();
			Users user = udao.findById(task.getAcceptId());
			int userLevel = user.getUserLevel()+1;
			user.setUserLevel(userLevel);
			udao.merge(user);

		}
		mav.addObject("success","修改任务成功！重新进入个人主页...");
		mav.addObject("user", task.getUsers());
		return mav;
	}
	
	@RequestMapping(value = "/User/{uid}.htm", method = RequestMethod.GET)
	public ModelAndView user(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int uid) {

		ModelAndView mav = new ModelAndView("/UserPage");
		UsersDAO udao = new UsersDAO();
		Users user = udao.findById(uid);
		Set<Tasks> task = service.getTask(uid);
		
		AcceptsDAO adao = new AcceptsDAO();
		List acceptList = adao.findAll();
		
		List tasksList = service.getList();
		mav.addObject("Tasks", tasksList);
		mav.addObject("user",user);
		mav.addObject("task", task);
		mav.addObject("acceptList", acceptList);
		
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView("redirect:/index.htm");
		return mav;
	}
	
	@RequestMapping(value = "/addtask/{uid}.htm", method = RequestMethod.POST)
	public ModelAndView addtask(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int uid)throws ParseException,
			IllegalStateException, IOException{

		ModelAndView mav = new ModelAndView("/success2");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		UsersDAO udao = new UsersDAO();
		Users us = udao.findById(uid);
		String taskContent  = request.getParameter("taskContent");
		Double reward = Double.parseDouble(request.getParameter("reward"));
		Date releaseTime = Date.valueOf(request.getParameter("releaseTime"));
		Date stopTime = Date.valueOf(request.getParameter("stopTime"));
		Boolean ifComplete = false;
		Tasks task = new Tasks();
		task.setUsers(us);
		task.setTaskContent(taskContent);
		task.setReward(reward);
		task.setReleaseTime(releaseTime);
		task.setStopTime(stopTime);
		task.setIfComplete(ifComplete);
	   service.add(task);
	   
		mav.addObject("success","发布任务成功！重新进入个人主页...");
		mav.addObject("user", us);
		return mav;
	}
}

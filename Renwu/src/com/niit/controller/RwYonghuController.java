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

import com.niit.dao.RwRenlingDAO;
import com.niit.dao.RwXiaoquDAO;
import com.niit.dao.RwXuexiaoDAO;
import com.niit.dao.RwXuqiuDAO;
import com.niit.dao.RwXuqiufenleiDAO;
import com.niit.model.*;
import com.niit.service.RwYonghuService;

@Controller
public class RwYonghuController {

	@Autowired 
	private RwYonghuService service;
	
	@RequestMapping(value = "/UserPage/{id}.htm", method = RequestMethod.GET)
	public ModelAndView UserPage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {
		ModelAndView mav = new ModelAndView("/UserPage");
		RwYonghu user = service.get(id);
		List userList = service.getList();
		RwXuqiuDAO xuqiudao = new RwXuqiuDAO();
		List<RwXuqiu> xuqiu=xuqiudao.findByYonghuId(id);
		List xuqiuList = xuqiudao.findAll();
		RwXuqiufenleiDAO fenleidao = new RwXuqiufenleiDAO();
		List fenleiList = fenleidao.findAll();
		RwXiaoquDAO xiaoqudao = new RwXiaoquDAO();
		List xiaoquList = xiaoqudao.findAll();
		RwXuexiaoDAO xuexiaodao = new RwXuexiaoDAO();
		List xuexiaoList = xuexiaodao.findAll();
		RwRenlingDAO renlingdao = new RwRenlingDAO();
		List<RwRenling> relingList = renlingdao.findByYonghuId(id);
		
		mav.addObject("user", user);
		mav.addObject("userList", userList);
		mav.addObject("xuqiu", xuqiu);
		mav.addObject("xuqiuList", xuqiuList);
		mav.addObject("fenleiList", fenleiList);
		mav.addObject("xiaoquList", xiaoquList);
		mav.addObject("xuexiaoList", xuexiaoList);
		mav.addObject("relingList", relingList);
		return mav;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String yonghuName = request.getParameter("yonghuName");
		String yonghuTrueName = request.getParameter("yonghuTrueName");
		String yonghuPwd = request.getParameter("yonghuPwd");
		String yonghuPhone = request.getParameter("yonghuPhone");
		int xiaoquId = Integer.parseInt(request.getParameter("xiaoquId"));
		List<RwYonghu> user = service.findByYonghuName(yonghuName);
		if(user.size()==0){
			RwYonghu yonghu = new RwYonghu();
			yonghu.setYonghuName(yonghuName);
			yonghu.setYonghuTrueName(yonghuTrueName);
			yonghu.setYonghuPwd(yonghuPwd);
			yonghu.setYonghuPhone(yonghuPhone);
			yonghu.setXiaoquId(xiaoquId);
			service.add(yonghu);
			mav.setViewName("redirect:/views/login.jsp");
		}else{
			mav.setViewName("/error2");
			mav.addObject("error","用户名已存在！");
		}
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
		String yonghuName = request.getParameter("yonghuName");
		String yonghuPwd = request.getParameter("yonghuPwd");
		List<RwYonghu> user = service.findByYonghuName(yonghuName);
		if(user.size()!=0){
			if(service.login(yonghuName, yonghuPwd)){
				mav.setViewName("/success");
				mav.addObject("success","登录成功！进入个人主页...");
				mav.addObject("user", user.get(0));
			}else{
				mav.setViewName("/error");
				mav.addObject("error","密码错误！");
			}
		}else{
			mav.setViewName("/error");
			mav.addObject("error","用户名错误！");
		}
		return mav;
	}
}

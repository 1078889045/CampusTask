package com.niit.controller;

import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.RwXuqiuDAO;
import com.niit.dao.RwYonghuDAO;
import com.niit.model.*;
import com.niit.service.RwXuqiuService;

@Controller
public class RwXuqiuController {

	@Autowired 
	private RwXuqiuService service;
	
	@RequestMapping(value = "/addxuqiu/{id}.htm", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id)throws ParseException,
			IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		if (id > 0) {
			System.out.println(id);
		} else {
			System.out.println(id + "---不对---");
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String xuqiuTitle =request.getParameter("xuqiuTitle");
		String xuqiuContent =request.getParameter("xuqiuContent");
		Double xuqiuMoney =Double.parseDouble(request.getParameter("xuqiuMoney"));
		Timestamp xuqiuAddTime =new Timestamp(System.currentTimeMillis());
		Timestamp xuqiuDeadLine =Timestamp.valueOf(request.getParameter("xuqiuDeadLine"));
		int xuqiuFenleiId =Integer.parseInt(request.getParameter("xuqiuFenleiId"));
		if(xuqiuDeadLine.compareTo(xuqiuAddTime)>=0){
			RwXuqiu xuqiu = new RwXuqiu();
			xuqiu.setXuqiuTitle(xuqiuTitle);
			xuqiu.setXuqiuContent(xuqiuContent);
			xuqiu.setXuqiuMoney(xuqiuMoney);
			xuqiu.setXuqiuAddTime(xuqiuAddTime);
			xuqiu.setXuqiuDeadLine(xuqiuDeadLine);
			xuqiu.setYonghuId(id);
			xuqiu.setXuqiuFenleiId(xuqiuFenleiId);
			service.add(xuqiu);
			mav.setViewName("/success2");
			mav.addObject("success","发布需求成功！重新进入个人主页...");
			mav.addObject("user", id);
		}else{
			mav.setViewName("/success2");
			mav.addObject("success","错误！截止时间早于发布时间！重新发布...");
			mav.addObject("user", id);
		}
		return mav;
	}		
	
	@RequestMapping(value = "/deleteXuqiu/{id}.htm", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView();
		String xuqiu = request.getParameter("xuqiu");
		String[] ids=xuqiu.split(";");
		boolean flag;
		if(ids.length>0){		
			flag=false;
				for(String idStr : ids){
	              int xuqiuId = Integer.parseInt(idStr);
	              service.deleteByID(xuqiuId);
	              //循环删除
	                           }
			}
		
		mav.setViewName("/success2");
		mav.addObject("success","删除成功！重新进入个人主页...");
		mav.addObject("user", id);
		return mav;
	}
}

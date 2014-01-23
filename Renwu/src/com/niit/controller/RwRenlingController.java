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

import com.niit.dao.RwPinjiaDAO;
import com.niit.dao.RwXuqiuDAO;
import com.niit.dao.RwXuqiufenleiDAO;
import com.niit.dao.RwYonghuDAO;
import com.niit.model.*;
import com.niit.service.RwRenlingService;

@Controller
public class RwRenlingController {

	@Autowired 
	private RwRenlingService service;	
	
	@RequestMapping(value = "/renling/{id}.htm", method = RequestMethod.GET)
	public ModelAndView renling(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView();
		List<RwRenling> relingList =service.findByXuqiuId(id);
		
		RwXuqiuDAO xuqiudao = new RwXuqiuDAO();
		RwXuqiu xuqiu = xuqiudao.findById(id);
		int yonghuId = xuqiu.getYonghuId();
		int fenleiId = xuqiu.getXuqiuFenleiId();
		RwYonghuDAO yhdao = new RwYonghuDAO();
		RwYonghu yonghu = yhdao.findById(yonghuId);
		List yonghuList = yhdao.findAll();
		
		RwXuqiufenleiDAO fldao = new RwXuqiufenleiDAO();
		RwXuqiufenlei fenlei = fldao.findById(fenleiId);
		
		
		mav.setViewName("/SingleXuqiu");
		mav.addObject("relingList", relingList);
		mav.addObject("yonghu", yonghu);
		mav.addObject("yonghuList", yonghuList);
		mav.addObject("fenlei", fenlei);
		mav.addObject("xuqiu", xuqiu);
		return mav;
	}
	
	@RequestMapping(value = "/choose/{id}.htm", method = RequestMethod.GET)
	public ModelAndView choose(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView();
		RwRenling renling = service.get(id);
		Boolean renlingIsDeal = true;
		List<RwRenling> rl =service.findByRenlingIsDeal(renlingIsDeal);
		if(rl.size()==0){
			renling.setRenlingIsDeal(renlingIsDeal);
			service.update(renling);
			mav.setViewName("/success3");
			mav.addObject("success","选择成功！重新进入认领信息页面...");
			mav.addObject("xuqiuId", renling.getXuqiuId());
		}else{
			mav.setViewName("/success3");
			mav.addObject("success","不能选择！重新进入认领信息页面...");
			mav.addObject("xuqiuId", renling.getXuqiuId());
		}
		return mav;
	}
	
	@RequestMapping(value = "/pingjia", method = RequestMethod.POST)
	public ModelAndView pingjia(HttpServletRequest request,
			HttpServletResponse response)throws ParseException,
			IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int xuqiuId =Integer.parseInt(request.getParameter("xuqiuId"));
		
		int pinjiaZhurenValue =Integer.parseInt(request.getParameter("pinjiaZhurenValue"));
		String pinjiaZhurenContent =request.getParameter("pinjiaZhurenContent");
		Timestamp pinjiaZhurenAddTime =new Timestamp(System.currentTimeMillis());
		RwPinjiaDAO pjdao = new RwPinjiaDAO();
		List<RwPinjia> pinjialist = pjdao.findByXuqiuId(xuqiuId);
		if(pinjialist.size() != 0){
			RwPinjia  pinjia= pinjialist.get(0);
			pinjia.setPinjiaZhurenAddTime(pinjiaZhurenAddTime);
			pinjia.setPinjiaZhurenValue(pinjiaZhurenValue);
			pinjia.setPinjiaZhurenContent(pinjiaZhurenContent);
			pinjia.setXuqiuId(xuqiuId);
			pjdao.merge(pinjia);
		}else{
			RwPinjia pinjia2 = new RwPinjia();
			pinjia2.setPinjiaZhurenAddTime(pinjiaZhurenAddTime);
			pinjia2.setPinjiaZhurenValue(pinjiaZhurenValue);
			pinjia2.setPinjiaZhurenContent(pinjiaZhurenContent);
			pinjia2.setXuqiuId(xuqiuId);
			pjdao.save(pinjia2);
		}
		mav.setViewName("/success4");
		mav.addObject("success","评价成功！重新进入认领信息页面...");
		mav.addObject("xuqiuId", xuqiuId);
		return mav;
	}
	
	@RequestMapping(value = "/rlpingjia", method = RequestMethod.POST)
	public ModelAndView rlpingjia(HttpServletRequest request,
			HttpServletResponse response)throws ParseException,
			IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int xuqiuId =Integer.parseInt(request.getParameter("xuqiuId"));
		int yonghuId =Integer.parseInt(request.getParameter("yonghuId"));
		int pinjiaRenlingValue =Integer.parseInt(request.getParameter("pinjiaRenlingValue"));
		String pinjiaRenlingContent =request.getParameter("pinjiaRenlingContent");
		Timestamp pinjiaRenlingAddTime =new Timestamp(System.currentTimeMillis());
		RwPinjiaDAO pjdao = new RwPinjiaDAO();
		List<RwPinjia> pinjialist = pjdao.findByXuqiuId(xuqiuId);
		if(pinjialist.size() != 0){
			RwPinjia  pinjia= pinjialist.get(0);
			pinjia.setPinjiaRenlingAddTime(pinjiaRenlingAddTime);
			pinjia.setPinjiaRenlingValue(pinjiaRenlingValue);
			pinjia.setPinjiaRenlingContent(pinjiaRenlingContent);
			pinjia.setXuqiuId(xuqiuId);
			pjdao.merge(pinjia);
		}else{
			RwPinjia pinjia2 = new RwPinjia();
			pinjia2.setPinjiaRenlingAddTime(pinjiaRenlingAddTime);
			pinjia2.setPinjiaRenlingValue(pinjiaRenlingValue);
			pinjia2.setPinjiaRenlingContent(pinjiaRenlingContent);
			pinjia2.setXuqiuId(xuqiuId);
			pjdao.save(pinjia2);
		}
		mav.setViewName("/success5");
		mav.addObject("success","评价成功！重新进入个人主页...");
		mav.addObject("user", yonghuId);
		return mav;
	}		
	
	@RequestMapping(value = "/addRenling", method = RequestMethod.POST)
	public ModelAndView addRenling(HttpServletRequest request,
			HttpServletResponse response)throws ParseException,
			IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int xuqiuId =Integer.valueOf(request.getParameter("xuqiuId"));
		int yonghuId =Integer.valueOf(request.getParameter("yonghuId"));
		String renlingContent =request.getParameter("renlingContent");
		
		List<RwRenling> renlingList = service.findByXuqiuId(xuqiuId);
		List<RwRenling> renlingList2 = service.findByYonghuId(yonghuId);
		RwXuqiuDAO xqdao = new RwXuqiuDAO();
		RwXuqiu xq = xqdao.findById(xuqiuId);
		Timestamp st = xq.getXuqiuDeadLine();
		int j =0;
		Timestamp renlingAddTime=new Timestamp(System.currentTimeMillis());
		if(st.compareTo(renlingAddTime)>=0){
			if(renlingList.size() !=0){
				for(int i=0;i<renlingList.size();i++){
					RwRenling renling = renlingList.get(i);
					Boolean isdeal = renling.getRenlingIsDeal();
					
						if(isdeal != false){						
							j+=1;
						}
						}
						if(j == 0){
							if(renlingList2.size() ==0){
							RwRenling renling = new RwRenling();
							renling.setRenlingAddTime(renlingAddTime);
							renling.setRenlingContent(renlingContent);
							renling.setXuqiuId(xuqiuId);
							renling.setYonghuId(yonghuId);
							renling.setRenlingIsDeal(false);
							service.add(renling);
							mav.setViewName("/success5");
							mav.addObject("success","添加认领信息成功！重新进入个人主页...");
							mav.addObject("user", yonghuId);
							}else{
								RwRenling renling2 = renlingList.get(0);
								int id =renling2.getRenlingId();
								System.out.print(id);
								renling2.setRenlingAddTime(renlingAddTime);
								renling2.setRenlingContent(renlingContent);
								renling2.setXuqiuId(xuqiuId);
								renling2.setYonghuId(yonghuId);
								renling2.setRenlingIsDeal(false);
								service.update(renling2);
								mav.setViewName("/success5");
								mav.addObject("success","您已抢过，修改认领信息成功！重新进入个人主页...");
								mav.addObject("user", yonghuId);
							}
						}else{
							mav.setViewName("/success5");
							mav.addObject("success","此需求已完成！重新进入个人主页...");
							mav.addObject("user", yonghuId);
						}	
				}else{
					RwRenling renling = new RwRenling();
					renling.setRenlingAddTime(renlingAddTime);
					renling.setRenlingContent(renlingContent);
					renling.setXuqiuId(xuqiuId);
					renling.setYonghuId(yonghuId);
					renling.setRenlingIsDeal(false);
					service.add(renling);
					mav.setViewName("/success5");
					mav.addObject("success","添加认领信息成功！重新进入个人主页...");
					mav.addObject("user", yonghuId);
				}
		}else{
			mav.setViewName("/success5");
			mav.addObject("success","此需求已过时！重新进入个人主页...");
			mav.addObject("user", yonghuId);
		}
		return mav;
	}
	
	@RequestMapping(value = "/deleteRenling/{id}.htm", method = RequestMethod.GET)
	public ModelAndView deleteRenling(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView();
		RwRenling renling = service.get(id);
		int yonghuId = renling.getYonghuId();
		mav.setViewName("/success2");
		mav.addObject("success","删除成功！重新进入个人主页...");
		mav.addObject("user", yonghuId);
		service.delete(renling);
		return mav;
	}
}

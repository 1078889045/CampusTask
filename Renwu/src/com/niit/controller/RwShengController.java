package com.niit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.RwQuDAO;
import com.niit.dao.RwShengDAO;
import com.niit.dao.RwShiDAO;
import com.niit.dao.RwXiaoquDAO;
import com.niit.dao.RwXuexiaoDAO;
import com.niit.dao.RwXuqiuDAO;
import com.niit.dao.RwXuqiufenleiDAO;
import com.niit.model.*;
import com.niit.service.RwShengService;

@Controller
public class RwShengController {
	
	/**
	 * 查询所有省份
	 * @return
	 */
	@RequestMapping(value = "/findProvince")
	public void findProvince(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		RwShengDAO dao = new RwShengDAO();
		List shengList = dao.findAll();
		for(int i = 0; i < shengList.size(); i++){
			json.put(i,shengList.get(i));
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}

	
	@RequestMapping(value = "/findXuexiao")
	public void findXuexiao(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		RwXuexiaoDAO dao = new RwXuexiaoDAO();
		List xuexiaoList = dao.findAll();
		for(int i = 0; i < xuexiaoList.size(); i++){
			json.put(xuexiaoList.get(i),xuexiaoList.get(i));
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value = "/findLeibie")
	public void findLeibie(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		RwXuqiufenleiDAO dao = new RwXuqiufenleiDAO();
		List leibieList = dao.findAll();
		for(int i = 0; i < leibieList.size(); i++){
			json.put(leibieList.get(i),leibieList.get(i));
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}
	/**
	 * 查询某省的所有城市
	 */
	@RequestMapping(value = "/findCityByProvinceId",method = RequestMethod.GET)
	public void findCityByProvinceId(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		int shengId =Integer.parseInt(request.getParameter("shengId"));
		RwShiDAO dao = new RwShiDAO();		
			List<RwShi> shiList= dao.findByShengId(shengId);
			for(int i = 0; i < shiList.size(); i++){
				json.put(shiList.get(i).getShiId(), shiList.get(i).getShiName());
			}
			try {
				out = response.getWriter();
				out.write(json.toString());
				out.flush(); 
				out.close();
			} catch (Exception e) {
				
			}
	}
	
	/**
	 * 查询某市的所有区县
	 * @return
	 */
	@RequestMapping(value = "/findDistrictByCityId",method = RequestMethod.GET)
	public void findDistrictByCityId(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		int shiId = Integer.parseInt(request.getParameter("cityId"));
		RwQuDAO dao = new RwQuDAO();
		List<RwQu> quList = dao.findByShiId(shiId);
		for(int i = 0; i < quList.size(); i++){
			json.put(quList.get(i).getQuId(),quList.get(i).getQuName());
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value = "/findXiaoquByxuexiaoId",method = RequestMethod.GET)
	public void findXiaoquByxuexiaoId(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		int xuexiaoId = Integer.parseInt(request.getParameter("xuexiaoId"));
		RwXiaoquDAO dao = new RwXiaoquDAO();
		List<RwXiaoqu> xiaoquList = dao.findByXuexiaoId(xuexiaoId);
		for(int i = 0; i < xiaoquList.size(); i++){
			json.put(xiaoquList.get(i).getXiaoquId(),xiaoquList.get(i).getXiaoquName());
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value = "/findXuqiuByleibieId",method = RequestMethod.GET)
	public void findXuqiuByliebieId(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		int leibieId = Integer.parseInt(request.getParameter("leibieId"));
		RwXuqiuDAO dao = new RwXuqiuDAO();
		List<RwXuqiu> xuqiuList = dao.findByXuqiuFenleiId(leibieId);
		for(int i = 0; i < xuqiuList.size(); i++){
			json.put(xuqiuList.get(i),xuqiuList.get(i));
		}
		try {
			out = response.getWriter();
			out.write(json.toString());
			out.flush(); 
			out.close();
		} catch (Exception e) {
			
		}
	}
}

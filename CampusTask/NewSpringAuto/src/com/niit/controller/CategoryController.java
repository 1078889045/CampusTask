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

import com.niit.model.*;
import com.niit.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired 
	private CategoryService service;
	
	@RequestMapping(value = "/index")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("/index");
		List categoryList = service.getList();
		System.out.println(categoryList);
		mav.addObject("categoryList", categoryList);
		return mav;
	}
	
	@RequestMapping(value = "/del/{id}.htm", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView("redirect:/index.htm");
		if (id > 0) {
			System.out.println(id);
		} else {
			System.out.println(id + "---²»¶Ô---");
		}
		service.deleteByID(id);

		return mav;
	}
	
	@RequestMapping(value = "/edit/{id}.htm", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		ModelAndView mav = new ModelAndView("/edit");
		Category category = service.get(id);
		System.out.println(category.getCatName());
		mav.addObject("category", category);
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView("redirect:/index.htm");
		
		String catName  = new String(request.getParameter("catName").getBytes("ISO-8859-1"), "GBK");
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = new Category();
		category.setId(id);
		category.setCatName(catName);
		
	   service.update(category);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView("redirect:/index.htm");
		String catName  = new String(request.getParameter("catName").getBytes("ISO-8859-1"), "GBK");
		Category category = new Category();
		category.setCatName(catName);
	   service.add(category);
		return mav;
	}
}

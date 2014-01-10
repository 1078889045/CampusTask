package com.niit.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.CategoryDAO;
import com.niit.model.Category;

@Service
public class CategoryService {
	@Autowired 
	public CategoryDAO dao;

	public void add(Category category){
		dao.save(category);
	}
	
	public void update(Category category){
		dao.merge(category);
	}
	
	public void delete(Category category) {
		dao.delete(category);
	}
	
	public void deleteByID(int id) {
		Category category  = dao.findById(id); 
		dao.delete(category);
	}
	
	public Category get(int id) {
		return dao.findById(id);
	}


	public List getList() {
		return dao.findAll();
	}
}

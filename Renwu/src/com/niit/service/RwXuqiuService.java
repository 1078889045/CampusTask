package com.niit.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.RwXuqiuDAO;
import com.niit.model.RwXuqiu;


@Service
public class RwXuqiuService {
	@Autowired 
	public RwXuqiuDAO dao;

	public void add(RwXuqiu xuqiu){
		dao.save(xuqiu);
	}
	
	public void update(RwXuqiu xuqiu){
		dao.merge(xuqiu);
	}
	
	public void delete(RwXuqiu xuqiu) {
		dao.delete(xuqiu);
	}
	
	public void deleteByID(int id) {
		RwXuqiu xuqiu  = dao.findById(id); 
		dao.delete(xuqiu);
	}
	
	public RwXuqiu get(int id) {
		return dao.findById(id);
	}


	public List getList() {
		return dao.findAll();
	}
	
	
}

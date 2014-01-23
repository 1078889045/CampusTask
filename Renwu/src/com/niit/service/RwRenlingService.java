package com.niit.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.RwRenlingDAO;
import com.niit.model.RwRenling;


@Service
public class RwRenlingService {
	@Autowired 
	public RwRenlingDAO dao;

	public void add(RwRenling renling){
		dao.save(renling);
	}
	
	public void update(RwRenling renling){
		dao.merge(renling);
	}
	
	public void delete(RwRenling renling) {
		dao.delete(renling);
	}
	
	public void deleteByID(int id) {
		RwRenling renling  = dao.findById(id); 
		dao.delete(renling);
	}
	
	public RwRenling get(int id) {
		return dao.findById(id);
	}


	public List getList() {
		return dao.findAll();
	}
	
	public List<RwRenling> findByXuqiuId(int id){
		return dao.findByXuqiuId(id);
	}
	
	public List<RwRenling> findByYonghuId(int id){
		return dao.findByYonghuId(id);
	}
	
	public List<RwRenling> findByRenlingIsDeal(Boolean renlingIsDeal){
		return dao.findByRenlingIsDeal(renlingIsDeal);
	}
}

package com.niit.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.RwYonghuDAO;
import com.niit.model.RwYonghu;


@Service
public class RwYonghuService {
	@Autowired 
	public RwYonghuDAO dao;

	public void add(RwYonghu user){
		dao.save(user);
	}
	
	public void update(RwYonghu user){
		dao.merge(user);
	}
	
	public void delete(RwYonghu user) {
		dao.delete(user);
	}
	
	public void deleteByID(int id) {
		RwYonghu user  = dao.findById(id); 
		dao.delete(user);
	}
	
	public RwYonghu get(int id) {
		return dao.findById(id);
	}


	public List getList() {
		return dao.findAll();
	}
	
	public List<RwYonghu> findByYonghuName(String yonghuName){
		return dao.findByYonghuName(yonghuName);
		
	}
	public boolean login(String yonghuName,String yonghuPwd){
		List<RwYonghu> a = dao.findByYonghuName(yonghuName);
		List<RwYonghu> b = dao.findByYonghuPwd(yonghuPwd);
		if(b.containsAll(a)){
			return true;
		}else{
			return false;
		}
	}
}

package com.niit.service;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.AcceptsDAO;
import com.niit.dao.TasksDAO;
import com.niit.model.Accepts;
import com.niit.model.Tasks;
@Service
public class AcceptsService {
	@Autowired
	public AcceptsDAO dao;
	public void add(Accepts accepts){
		dao.save(accepts);
	}
	
	public void update(Accepts accepts){
		dao.merge(accepts);
	}
	
	public void delete(Accepts accepts) {
		dao.delete(accepts);
	}
	
	public Accepts get(int id) {
		return dao.findById(id);
	}
	

	public List getList() {
		return dao.findAll();
	}
}

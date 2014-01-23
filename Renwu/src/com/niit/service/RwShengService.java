package com.niit.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.RwShengDAO;
import com.niit.model.RwSheng;

@Service
public class RwShengService {
	@Autowired 
	public RwShengDAO dao;

	public List getList() {
		return dao.findAll();
	}
}

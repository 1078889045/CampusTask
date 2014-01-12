package com.niit.service;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.UsersDAO;
import com.niit.model.Users;
@Service
public class UsersService {
	@Autowired
	public UsersDAO dao;
	public void add(Users users){
		dao.save(users);
	}
	
	public void update(Users users){
		dao.merge(users);
	}
	
	public void delete(Users users) {
		dao.delete(users);
	}
	
	public void deleteByID(int id) {
		Users users  = dao.findById(id); 
		dao.delete(users);
	}
	
	public Users get(int id) {
		return dao.findById(id);
	}


	public List getList() {
		return dao.findAll();
	}
	
	public boolean login(String userName,String userPassword){
		List<Users> a = dao.findByUserName(userName);
		List<Users> b = dao.findByUserPassword(userPassword);
		if(b.containsAll(a)){
			return true;
		}else{
			return false;
		}
	}
}

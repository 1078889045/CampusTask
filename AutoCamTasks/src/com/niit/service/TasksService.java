package com.niit.service;
import java.util.List;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.TasksDAO;
import com.niit.dao.UsersDAO;
import com.niit.model.Tasks;
import com.niit.model.Users;
@Service
public class TasksService {
	@Autowired
	public TasksDAO dao;
	public void add(Tasks tasks){
		dao.save(tasks);
	}
	
	public void update(Tasks tasks){
		dao.merge(tasks);
	}
	
	public void delete(Tasks tasks) {
		dao.delete(tasks);
	}
	
	public void deleteByID(int id) {
		Tasks tasks  = dao.findById(id); 
		dao.delete(tasks);
	}
	
	public Tasks get(int id) {
		return dao.findById(id);
	}

	public Set<Tasks> getTask(int uid){
		UsersDAO udao = new UsersDAO();
		Users user  = udao.findById(uid);
		Set<Tasks> task = user.getTaskses();
		return task;
	}
	public List getList() {
		return dao.findAll();
	}
}

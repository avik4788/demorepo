package com.company.service;

import java.util.List;

import com.company.constants.Roles;
import com.company.entities.User;

public interface UserService {
	void save(User user, Roles role);
	
	void deleteUser(long id);
	
	List<User> getUsers();
	
	User findByUserName(String userName);
}

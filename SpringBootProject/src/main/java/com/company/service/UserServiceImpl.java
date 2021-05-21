package com.company.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.constants.Roles;
import com.company.entities.Role;
import com.company.entities.User;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user, Roles role) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepo.findByName(role.toString()));
		user.setRoles(roles);
		
		userRepo.save(user);
	}

	@Override
	public void deleteUser(long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			userRepo.delete(user.get());
		}
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}
}

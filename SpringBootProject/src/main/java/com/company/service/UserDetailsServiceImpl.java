package com.company.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.entities.User;
import com.company.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepo.findByUserName(userName);
		
		if(user == null) {
			throw new UsernameNotFoundException(userName);
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		user.getRoles().forEach(eachRole -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(eachRole.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

}

package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.constants.Roles;
import com.company.entities.User;
import com.company.service.SecurityService;
import com.company.service.UserService;
import com.company.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		
		userService.save(userForm, Roles.ROLE_USER);
		securityService.autoLogin(userForm.getUserName(), userForm.getPasswordConfirm());
		
		return "redirect:/welcome";
	}
	
	@GetMapping("/adminregistration")
	public String adminRegistration(Model model) {
		model.addAttribute("userForm", new User());
		
		return "adminRegistration";
	}
	
	@PostMapping("/adminregistration")
	public String adminRegistration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		
		userService.save(userForm, Roles.ROLE_ADMIN);
		securityService.autoLogin(userForm.getUserName(), userForm.getPasswordConfirm());
		
		return "redirect:/list-users";
	}
	
	@GetMapping({"/", "/welcome"})
	public String welcome(Model model) {
		String roles = getCurrentRoles();
		
		if(roles.contains(Roles.ROLE_ADMIN.toString())) {
			return "redirect:list-users";
		}else {
			return "welcome";
		}
	}
	
	@GetMapping("/list-users")
	public String listUsers(ModelMap model) {
		List<User> users = userService.getUsers();
		model.put("users", users);
		
		return "list-users";
	}
	
	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteUser(@RequestParam long id) {
		userService.deleteUser(id);
		
		return "redirect:list-users";
	}
	
	private String getCurrentRoles() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}
}
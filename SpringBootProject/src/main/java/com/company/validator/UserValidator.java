package com.company.validator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.company.entities.User;
import com.company.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "notempty");
		
		if(user.getUserName().length() < 6 || user.getUserName().length() > 32) {
			errors.rejectValue("userName", "userform.invalid.username.size");
		}
		
		if(null != userService.findByUserName(user.getUserName())) {
			errors.rejectValue("userName", "userform.duplicate.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notempty");
		
		if(user.getPassword().length() < 6 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "userform.invalid.password.size");
		}
		
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "userform.password.confirm.difference");
		}
	}
}

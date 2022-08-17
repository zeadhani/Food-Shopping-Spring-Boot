package com.example.demospringsecurityclient.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.demospringsecurityclient.entity.User;
import com.example.demospringsecurityclient.model.CustomUserDetails;
import com.example.demospringsecurityclient.model.UserProfile;
import com.example.demospringsecurityclient.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	//@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/users/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	//@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = "/del/{userId}")
	public boolean deleteUser(@PathVariable(value = "userId") Long id) {
		return userService.deleteEmployeeById(id);
	}
	

	
	@GetMapping(value = "/getuserdetails")
	 public User currentUserName(HttpServletRequest request) {
		String username=request.getUserPrincipal().getName();
		User user=userService.getUserByEmail(username);
		 return user;
	    }
	
}

package com.example.demospringsecurityclient.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringsecurityclient.model.UserAuth;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
 
	@PostMapping(value = "/login")
    public void login(@RequestBody UserAuth userAuth){
		
		
	}
}

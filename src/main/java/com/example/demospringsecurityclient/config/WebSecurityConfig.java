package com.example.demospringsecurityclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demospringsecurityclient.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserDetailsService userDetailsService;
	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	public static final String[] WHITE_LIST_URLS= {
			"/register",
			"/verifyRegistration*",
			"/resendverifytoken*",
			"/resetpassword",
			"/savePassword"	,
			"/",
			"/products/all",
			"/categories/all",
			"/v3/api-docs/**",
			"/swagger-ui/**"
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {	
		return new BCryptPasswordEncoder(11);
	}
	
	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeHttpRequests().antMatchers(WHITE_LIST_URLS).permitAll();	
//		return http.build();
//		
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(WHITE_LIST_URLS).permitAll()
		.anyRequest().authenticated().and().httpBasic()
		.and().csrf().disable();  
	}
	//.and().logout().logoutRequestMatcher( new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
	    @Bean
	    AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider provider 
	                 = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(new BCryptPasswordEncoder());
	        return  provider;
	    }
	
	
}

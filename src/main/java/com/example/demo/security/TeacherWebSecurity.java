package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@Order(2)
public class TeacherWebSecurity  extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService teacherDetailsService;
	
	private PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt;
	}
	
	private AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder());
		dao.setUserDetailsService(teacherDetailsService);
		return dao;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.antMatcher("/teacher/**").authorizeRequests().antMatchers("/teacher/**")
		.hasAuthority("TEACHER").anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/teacher/login").permitAll()
		.defaultSuccessUrl("/teacher/mainview",true)
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/teacher/logout"))
		.logoutSuccessUrl("/teacher/login").permitAll();
		
		
	}

	
	
	
}

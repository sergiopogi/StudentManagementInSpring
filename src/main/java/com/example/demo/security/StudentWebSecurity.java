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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@Order(1)
public class StudentWebSecurity  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService studentDetailsService;
	
	private PasswordEncoder passwordEncoder() {
	 BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		 return bcryptPasswordEncoder;
	}
	
	private AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(studentDetailsService);
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.antMatcher("/student/**").authorizeRequests().antMatchers("/student/**")
		.hasAuthority("STUDENT").anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/student/login").permitAll()
		.defaultSuccessUrl("/student/mainview",true)
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/student/logout"))
		.logoutSuccessUrl("/student/login").permitAll();
		
	
		
	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable();
////		http.authorizeRequests().antMatchers("/customer/sign-up").permitAll();
//		http.antMatcher("/student/**").authorizeRequests().anyRequest().authenticated()
////		http.authorizeRequests().antMatchers("/student/**").hasAuthority("STUDENT").anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/student/login").permitAll()
//		.defaultSuccessUrl("/student/mainview",true)
//		.and()
//		.logout().invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
//		.logoutSuccessUrl("/").permitAll();
//		
//	}
	
	
}

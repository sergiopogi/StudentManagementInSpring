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

import com.example.demo.entities.Roles;

@EnableWebSecurity
@Configuration
@Order(3)
public class AdminWebSecurity extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private UserDetailsService adminDetailsService;
//	
//	private PasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		return bcrypt;
//	}
//	
//	private AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//		dao.setPasswordEncoder(passwordEncoder());
//		dao.setUserDetailsService(adminDetailsService);
//		return dao;
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
		auth.inMemoryAuthentication().withUser("admin").password("{bcrypt}$2a$10$LaAC9nPe7MFOS8S1Nu6ps.uEl6l11oStw980S27EhAQJ6ahhIq6OK").authorities(Roles.adminRole);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.antMatcher("/admin/**").authorizeRequests().antMatchers("/admin/**")
		.hasAuthority("ADMIN").anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/admin/login").permitAll()
		.defaultSuccessUrl("/admin/mainview",true)
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
		.logoutSuccessUrl("/admin/login").permitAll();
		
		
	}

}

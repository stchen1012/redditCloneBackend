package com.ga.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ga.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.ga")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean("encoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
	   CorsConfiguration configuration = new CorsConfiguration();
	   configuration.setAllowedOrigins(Arrays.asList("*"));
	   configuration 
	       .setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
	   configuration.setAllowedHeaders(
	       Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "cache-control"));
	   configuration.setAllowCredentials(true);
	   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   source.registerCorsConfiguration("/**", configuration);
	   return source;
	 }
	
	@Autowired
	UserService userService;
	
	@Autowired
    private JwtRequestFilter jwtRequestFilter;
	
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	
	//TODO - add auth to post and comment
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and()
 			.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/user/signup/**", "/user/login/**").permitAll()
	        .antMatchers("/user/**", "/profile/**").authenticated()
	        .and()
	        .httpBasic()
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

package com.demo.studentSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.studentSecurity.filter.JwtFilter;
import com.demo.studentSecurity.service.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailService;
	
	@Autowired
	private JwtFilter filter;
	
	@Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }
     
  
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/student/authenticate").permitAll()
        						.antMatchers("/student/addStudent").hasAnyAuthority("student","admin")
        						.antMatchers("/student/**").hasAuthority("admin")
								.anyRequest().authenticated().and().exceptionHandling().
								and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean(name= BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	
	

}

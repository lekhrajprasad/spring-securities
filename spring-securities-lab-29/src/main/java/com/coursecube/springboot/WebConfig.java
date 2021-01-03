package com.coursecube.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		//Setup AuthenticationManager
		try{
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("lekhraj").password("{noop}lekhraj").roles("ADMIN");
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("prasad").password("{noop}prasad").roles("STOREKEEPER");
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("customer1").password("{noop}customer1").roles("CUSTOMER");
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("customer2").password("{noop}customer2").roles("CUSTOMER");
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("super").password("{noop}super").roles("CUSTOMER","ADMIN");
		}catch (Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try{
			http.authorizeRequests()
					.antMatchers("/deleteBook**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/addBook**").access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
					.antMatchers("/editBook**").access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
					.antMatchers("/placeOrder**").access("hasAnyRole('ROLE_CUSTOMER')")
					.and()
					.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error")
					.usernameParameter("myusername")
					.passwordParameter("mypassword")
					.and()
					.logout()
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/login?logout")
					.and()
					.exceptionHandling().accessDeniedPage("/WEB-INF/myjsps/invalidAccess.jsp");
		}catch (Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}

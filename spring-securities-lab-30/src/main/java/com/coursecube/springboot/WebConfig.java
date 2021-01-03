package com.coursecube.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@SpringBootApplication
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		//Setup AuthenticationManager
		String usersQuery="select username,password, active from myusers where username=?";
		String rolesQuery="select username, role from myroles where username=?";
		try{
			authenticationManagerBuilder.jdbcAuthentication()
					.dataSource(dataSource)
					.usersByUsernameQuery(usersQuery)
					.authoritiesByUsernameQuery(rolesQuery);
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
					.exceptionHandling().accessDeniedPage("/WEB-INF/myjsps/invalidAccess.jsp")
					.and()
					.csrf()
					.and()
					.sessionManagement()
					.enableSessionUrlRewriting(true)
					.maximumSessions(1);
		}catch (Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
		@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);
		return bCryptPasswordEncoder;
	}
}

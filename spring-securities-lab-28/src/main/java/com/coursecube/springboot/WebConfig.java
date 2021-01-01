package com.coursecube.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
//@EnableWebMvc
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/myjsps/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}*/
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		//Setup AuthenticationManager
		try{
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser("lekhraj").password("{noop}lekhraj").roles("ADMIN");
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
					.and()
					.formLogin()
					.and()
					.exceptionHandling().accessDeniedPage("/WEB-INF/myjsp/invalidAccess.jsp");
		}catch (Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}

package com.vvsk.app.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
				.antMatchers("/actuator/*", "/h2-console/**", "/api/**", "/token", "/", "/js/**", "/css/**", "/img/**",
						"/style.css")
				.permitAll().antMatchers("/process/**").hasAnyRole("ADMIN", "PROCESS_MANAGER")
				.antMatchers("/content/**").hasAnyRole("ADMIN", "CONTENT_MANAGER").antMatchers("/user/**")
				.hasAnyRole("ADMIN", "USER_MANAGER").antMatchers("/data/**").hasAnyRole("ADMIN", "DATA_MANAGER")
				.anyRequest().authenticated();
	}

}

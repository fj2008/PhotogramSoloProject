package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Bean어노테이션
	//SecurityConfig(해당클래스)가 Ioc에 등록될때 @Bean을 읽어서 return값을 들고있는다.
	//그럼 이제 들고와서 사용하기만하면된다.
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//현제 서버에서는 csrf토큰을 사용하지 않는다.
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/", "/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin")//get요청
			.loginProcessingUrl("/auth/signin")//post요청->스프링 시큐리티가 로그인프로세스진행
			.defaultSuccessUrl("/");
	}
	
}

package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //id할때 사용
@Controller //ioc에 등록 //파일을 리턴하겠다.
public class AuthController {
	
	private final AuthService authService;
	//자바에서는 전역 변수에 final이 걸려있으면 객체가 만들어질때 무조건 초기화를 해줘야한다.
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	//회원가입페이지로 이동하는 
	@GetMapping("/auth/signin")
	public String signinForm() {
		return"auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return"auth/signup";
	}
	
	// 회원가입 버튼-> /auth/signup ->/auth/signin
	@PostMapping("/auth/signup")
	public String signup( SignupDto signupDto) {
		User user = signupDto.toEntity();
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return"auth/signin";
	}
}

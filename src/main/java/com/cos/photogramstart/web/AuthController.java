package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.hendler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //id할때 사용
@Controller //ioc에 등록 //파일을 리턴하겠다.
public class AuthController {
	
	private final AuthService authService;
	//자바에서는 전역 변수에 final이 걸려있으면 객체가 만들어질때 무조건 초기화를 해줘야한다.
	
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
	//@ResponseBody는 @controller에서 데이터를 응답하도록 한다.
	// 해당메서드는 데이터를 응답하기때문에 파일을 리턴 할 수 없다. 
	//그래서 컨트롤 어드바이스를 만들어서 처리해야한다
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		//bindingResult.hasErrors()는 벨리데이션체크시에 오류가 났다면!hasErrors는 boolean타입
		//벨리데이션 체크시에 오류가 발생하면 그 오류를 getFieldErrors()컬렉션에 다 모아준다.
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("===============================");
				System.out.println(error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패",errorMap);
		}else {
			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return"auth/signin";
		}
		
	}
}

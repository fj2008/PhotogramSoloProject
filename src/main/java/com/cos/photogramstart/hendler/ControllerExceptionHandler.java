package com.cos.photogramstart.hendler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.hendler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;


@RestController
//@ControllerAdvice 해당클래스가 모든 Exception을 낙아채서 여기서 관리할 수 있게 해준다.
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다.
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
	}

}

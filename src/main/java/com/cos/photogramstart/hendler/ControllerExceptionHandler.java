package com.cos.photogramstart.hendler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.hendler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;


@RestController
//@ControllerAdvice 해당클래스가 모든 Exception을 낙아채서 여기서 관리할 수 있게 해준다.
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다.
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		
		return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
	}

}

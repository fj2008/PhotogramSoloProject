package com.cos.photogramstart.hendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.hendler.ex.CustomApiException;
import com.cos.photogramstart.hendler.ex.CustomValidationApiException;
import com.cos.photogramstart.hendler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;


@RestController
//@ControllerAdvice 해당클래스가 모든 Exception을 낙아채서 여기서 관리할 수 있게 해준다.
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다.
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
	}
	
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationException(CustomValidationApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
	}

}

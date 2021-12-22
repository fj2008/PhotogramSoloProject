package com.cos.photogramstart.hendler.ex;

import java.util.Map;

public class CustomException extends RuntimeException{

	//객체를 구분할때 사용한다.
	//jvm이 보는것
	private static final long serialVersionUID = 1L;

	
	public CustomException(String message) {
		super(message);//message의 getter는 부모가 들고있다.
	}  
	
}

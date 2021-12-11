package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
//1 ioc등록
//service어노테이션 등록객체는 트랜잭션을 관리해준다.
public class AuthService {

	private final UserRepository userRepository;
	public User 회원가입(User user) {
		//회원가입 진행
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}

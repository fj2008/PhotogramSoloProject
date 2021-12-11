package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
//1 ioc등록
//service어노테이션 등록객체는 트랜잭션을 관리해준다.
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;//bean이 걸려있어서 들고와서 사용하기만하면됨
	
	//@Transactional를 걸면
	//함수가 실행되고 종료될때 까지 트렌젝션 관리를 해준다.
	@Transactional //해당 트렌젝션은 Write할때 건다 (insert, Update, delete)
	public User 회원가입(User user) {
		//회원가입 진행
		String rawPassword = user.getPassword();//user 패스워드를 가져와서
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);//암호화를하고
		user.setPassword(encPassword);//암호화된 패스워드를 저장
		user.setRole("ROLE_USER"); //관리자 ROLE_ADMIN
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}

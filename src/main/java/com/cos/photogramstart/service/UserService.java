package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;
	
	@Transactional
	public User 회원수정(int id, User user) {
		//1. 영속화
		User userEntity = userRepository.findById(id).get();
		//opthina
				//1.무조건찾았다. 걱정마 get()
				//2. 못찾았어 익섹션발동시킬게 orElseThrow()
				//2. 영속화된 오브젝트를 수정- 더티체킹(업데이트완료)
		userEntity.setName(user.getName());
		System.out.println(user.getPassword());
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		//암호화된 페스워드를 업데이트해야한다.
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
		
	}//더티체킹이 일어나서 업데이트 완료된다.
}

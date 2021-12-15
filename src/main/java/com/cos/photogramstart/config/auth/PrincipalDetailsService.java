package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//패스워드는 알아서 채킹하니까 신경쓸 필요없다.
//리턴이 잘 되면 자동으로UserDetails타입을 세션으로 만들어준다.
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity= userRepository.findByUsername(username);
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);//PrincipalDetails가 세션에 저장이된다.
		}
		
	}

}

package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdatedto {

	private String name;// 필수
	private String password;// 필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//조금 위험함, 코드 수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password) // 패스워드를 기재 안 했으면 문제가 된다 그래서 Validation체크해야함
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}

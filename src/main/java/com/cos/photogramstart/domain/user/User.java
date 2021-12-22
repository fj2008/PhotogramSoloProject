package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder//빌터패턴 사용하겠다.
// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)
//ORM - 오브젝트 관점에서 테이블이 만들어진다.
@AllArgsConstructor// 전체 생성자 생성
@NoArgsConstructor//빈생성자 생성
@Entity //디비에 테이블을 생성
@Data
public class User {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 정략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 20,unique = true)//unique제약조건이 걸려서 아이디 중복시 가입이안된다.
	private String username;
	@Column(nullable = false)//null불가능
	private String password;
	@Column(nullable = false)//null불가능
	private String name;
	@Column(nullable = false)//null불가능
	private String email;
	private String website; //웹 사이트
	private String bio; //자기소개
	private String phone;
	private String gender;
	
	private String profileImageUrl;//사진
	private String role;//권한
	
	//user를 select할때 해당 User id로 등록된 image들을 다 가져와
	//mappedBy: 나는 연관관계의 주인이아니다. 그러므로 테이블에 컬럼만들지마
	//Lazy= User를 select할때 해당 userId로 등록된 image들을 가져오지마 -- 대신 getter호출될때 가져와!
	//EAGER =user를 slelct할때 해당 userid로 등록된 image들을 전부 join해서 가져와!
	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
	private List<Image> images;
	
	private LocalDateTime createDate;
	
	@PrePersist// @PrePersist어노테이션은 DB에 Insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}

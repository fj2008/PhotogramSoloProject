package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Data
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption; //오늘 나 너무 피곤해!
	private String postimageUrl; // 사진을 전송받아서 그 사진을 서버에 특정폴더에 저장 ->DB에 그 저장된 경로 insert
	
	@JsonIgnoreProperties("images")
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	//이미지 좋아요
	
	//댓글
	
	private LocalDateTime createDate;
	
	@PrePersist// @PrePersist어노테이션은 DB에 Insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

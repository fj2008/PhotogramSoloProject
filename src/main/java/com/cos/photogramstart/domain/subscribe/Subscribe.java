package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Data
@Table(//두개의 필드를 유니크 제약조건을 걸기위해서 사용
		uniqueConstraints = {
				@UniqueConstraint(
						name="subscribe_uk",
						columnNames = {"fromUserId","toUserId"}//실제 db의 컬럼명을 적어줘야한다.
			)
	}
)
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//구독하기 기능의 입장에서 구독하기가N이고 유저가1이기 때문에 현제테이블엔 ManyToOne을 건다.
	@JoinColumn(name = "fromUserId")//컬럼명을 커멜표기법으로 수정
	@ManyToOne
	private User formUser;//구독하는 유저
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser;//구독받는 유저
	
	private LocalDateTime createDate;
	
	@PrePersist// @PrePersist어노테이션은 DB에 Insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}

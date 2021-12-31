package com.cos.photogramstart.domain.likes;

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

import com.cos.photogramstart.domain.image.Image;
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
@Table(//두개의 필드를 유니크 제약조건을 걸기위해서 사용
		//왜냐하면 한 유저가 어떤이미지에 중복해서 좋아요를 누를 수 없기때문
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",
						columnNames = {"imageId","userId"}//실제 db의 컬럼명을 적어줘야한다.
			)
	}
)
public class Likes {//N

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//어떤 이미지를 누가 좋아했는지 가 필요하다
	
	@JoinColumn(name="imageId")
	@ManyToOne
	private Image image;//1
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;//1
	
	private LocalDateTime createDate;
	
	@PrePersist// @PrePersist어노테이션은 DB에 Insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}



package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
// :fromUserId처럼 : 을 사용하는이유는 아래매개변수를 바인딩해서 넣겠다는 문법입니다.
// native쿼리를짤때 nativeQuery = true 를 꼭 넣어줘야 발동합니다.

	@Modifying//INSERT,DELETE,UPDATE를 네이티브 쿼리로 작성하려면 해당어노테이션 필요!
	@Query(value= "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);
	
	@Modifying
	@Query(value= "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId= :toUserId",nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
}

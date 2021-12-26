package com.cos.photogramstart.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.hendler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribeDto.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // 모든 Repository는 EntityManager를 구현해져 만들어 져 있는 구현체
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId,int pageUserId){
		StringBuffer sb= new StringBuffer();
		sb.append("SELECT u.id, u.username , u.profileImageUrl, ");
		sb.append("IF ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId =u.id),1,0) subscribeState, ");
		sb.append("IF ((?=u.id),1,0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?");
		//쿼리 받을때 주의할점
		//1. 줄이 바뀔땐 마지막에 한칸띄어준다. 다음줄이랑 겹쳐 나오지도록 하기위함
		//2.맨끝에 세미콜론 첨부하면 안된다.
		
		//바인딩하기전에 물음표로 넣어줄 변수위치 확인
		//1.물음표 principalId
		//2.물음표 principalId
		//3.마지막 물음표 pageUserId
		
		//쿼리 완성
		//javax.persistens에 Query를 임포트해준다.
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
			
		//쿼리실행
		//QLRM라이브러리 필요 =DTO에 DB결과를 메핑하기 위해서
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos =  result.list(query, SubscribeDto.class);
		return subscribeDtos;
	}
	
	
	//둘다 DB에 영향을 주는것이니까@Transactional를 사용해 준다
	@Transactional
	public void  구독하기(int formUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(formUserId, toUserId);
		}catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
	}
	
	@Transactional
	public void  구독취소하기(int formUserId, int toUserId) {
	
		 subscribeRepository.mUnSubscribe(formUserId, toUserId);
		
	}
}

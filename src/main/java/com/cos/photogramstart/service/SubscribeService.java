package com.cos.photogramstart.service;


import java.util.List;

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
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId,int pageUserId){
		
		return null;
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

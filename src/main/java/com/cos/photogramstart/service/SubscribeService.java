package com.cos.photogramstart.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.hendler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	
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

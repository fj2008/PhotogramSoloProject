package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class imageService {

	private final ImageRepository imageRepository;
	
	//readOnly = true를 하면
	//영속성 컨텍스에서 변경감지를해서, 더티체킹후 flush(반영)을 하게되는데
	//readOnly = true를하면 DB에 반영을 하지 않게되서 프로그램이 더 깔끔해진다.
	@Transactional(readOnly = true)
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStroy(principalId, pageable);
		
		
		//2번으로 로그인 해서 2번이 구독중인 이미지가 들고와졌다.
		//images에 좋아요 상태 담기
		images.forEach((image)->{
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like) ->{
				if(like.getUser().getId() == principalId) {
					//좋아요를 한 이미지를 찾아서 현제 로그인한 사람이 좋아요한 것인지 비교
					
					image.setLikeState(true);
				}
			});
		});
		
		
		return images;
	}
	
	@Value("${file.path}")
	private String uploadFolder;
	
	//왜 @Transactional을 걸어줘야하
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName =uuid+"_"+ imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// image 테이블에 저장
		Image image= imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
		imageRepository.save(image);
		
	}
}

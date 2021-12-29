package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
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
	public List<Image> 이미지스토리(int principalId){
		List<Image> images = imageRepository.mStroy(principalId);
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

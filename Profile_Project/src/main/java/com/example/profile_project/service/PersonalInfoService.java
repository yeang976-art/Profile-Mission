package com.example.profile_project.service;

import com.example.profile_project.common.exception.ErrorCode;
import com.example.profile_project.common.exception.ServiceException;
import com.example.profile_project.dto.CreatePersonalInfoRequest;
import com.example.profile_project.dto.CreatePersonalInfoResponse;
import com.example.profile_project.dto.FileDownloadUrlResponse;
import com.example.profile_project.dto.FileUploadResponse;
import com.example.profile_project.dto.GetPersonalInfoResponse;
import com.example.profile_project.entity.PersonalInfo;
import com.example.profile_project.repository.PersonalInfoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private S3Service s3Service;

    // 프로필 등록
    @Transactional
    public CreatePersonalInfoResponse saveInfo(@Valid CreatePersonalInfoRequest request) {
        PersonalInfo personalInfo = personalInfoRepository.save(
                new PersonalInfo(request.name(), request.age(), request.height(), request.mbti()));

        return CreatePersonalInfoResponse.from(personalInfo);
    }

    // 프로필 단 건 조회
    @Transactional(readOnly = true)
    public GetPersonalInfoResponse getInfo(Long id) {
        PersonalInfo personalInfo = checkId(id);

        return GetPersonalInfoResponse.from(personalInfo);
    }

    // 특정 프로필에 이미지 등록
    @Transactional
    public FileUploadResponse saveImage(Long id, MultipartFile file) {
        PersonalInfo personalInfo = checkId(id);
        String key = s3Service.uploadFile(file); // 외부 서비스에서 받은 키를 저장
        personalInfo.saveImage(key); // 선택된 엔티티에 키 이름 저장
        personalInfoRepository.save(personalInfo); // DB에 반영 (Dirty Check 가능)
        return new FileUploadResponse(key);
    }

    // 특정 프로필 이미지의 URL을 Presigned로 다운로정
    @Transactional(readOnly = true)
    public FileDownloadUrlResponse downloadUrl(Long id) {
        PersonalInfo personalInfo = checkId(id);
        String key = personalInfo.getS3ImageKey();

        if (key == null) {
            log.error("[404] {}에 프로필 이미지 없음", id);
            throw new ServiceException(ErrorCode.IMAGE_NOT_FOUND);
        }

        URL url = s3Service.getDownloadUrl(key); // 선택된 key로 Presigned URL(유효기간 7일) 발급
        return new FileDownloadUrlResponse(url.toString());
    }

    private PersonalInfo checkId(Long id) {
        return personalInfoRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[404] {}는 이 DB에 없음", id);
                    return new ServiceException(ErrorCode.PERSON_INFO_NOT_FOUND);
                });
    }
}

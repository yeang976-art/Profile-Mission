package com.example.profile_project.service;

import com.example.profile_project.common.exception.ErrorCode;
import com.example.profile_project.common.exception.ServiceException;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7L);

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    // 파일 업로드하기
    public String uploadFile(MultipartFile file) {
        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());
            return key; // 버킷 안에 저장되는 키
        } catch (IOException e) {
            log.error("[500] S3 파일 업로드 실패: {}", e.getMessage()); // 콘솔 메세지 (포맷형으로도 구현 가능)
            throw new ServiceException(ErrorCode.UPLOAD_FILE_FAIL); // 클라이언트 메세지
        }
    }

    // Presigned URL 받기
    public URL getDownloadUrl(String key) {
        return s3Template.createSignedGetURL(bucket, key, PRESIGNED_URL_EXPIRATION);
    }
}

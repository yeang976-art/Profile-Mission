package com.example.profile_project.controller;

import com.example.profile_project.dto.FileDownloadUrlResponse;
import com.example.profile_project.dto.FileUploadResponse;
import com.example.profile_project.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final S3Service s3Service;

    @PostMapping("/files/upload")
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        String key = s3Service.uploadFile(file);
        return ResponseEntity.ok(new FileUploadResponse(key));
    }

    // Presigned URL
    @GetMapping("/files/download-url")
    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@RequestParam String key) {
        URL url = s3Service.getDownloadUrl(key);
        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
    }
}

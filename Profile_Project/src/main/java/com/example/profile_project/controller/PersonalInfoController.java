package com.example.profile_project.controller;

import com.example.profile_project.dto.CreatePersonalInfoRequest;
import com.example.profile_project.dto.CreatePersonalInfoResponse;
import com.example.profile_project.dto.GetPersonalInfoResponse;
import com.example.profile_project.service.PersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @PostMapping
    public ResponseEntity<CreatePersonalInfoResponse> register(@Valid @RequestBody CreatePersonalInfoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personalInfoService.saveInfo(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPersonalInfoResponse> getId(@PathVariable Long id) {
        return ResponseEntity.ok(personalInfoService.getInfo(id));
    }
}

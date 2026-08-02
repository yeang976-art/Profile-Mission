package com.example.profile_project.service;

import com.example.profile_project.dto.CreatePersonalInfoRequest;
import com.example.profile_project.dto.CreatePersonalInfoResponse;
import com.example.profile_project.dto.GetPersonalInfoResponse;
import com.example.profile_project.entity.PersonalInfo;
import com.example.profile_project.repository.PersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;

    @Transactional
    public CreatePersonalInfoResponse saveInfo(CreatePersonalInfoRequest request) {
        PersonalInfo personalInfo = personalInfoRepository.save(
                new PersonalInfo(request.name(), request.age(), request.height(), request.mbti()));

        return CreatePersonalInfoResponse.from(personalInfo);
    }

    @Transactional(readOnly = true)
    public GetPersonalInfoResponse getInfo(Long id) {
        PersonalInfo personalInfo = personalInfoRepository.findById(id).orElseThrow();

        return GetPersonalInfoResponse.from(personalInfo);
    }
}

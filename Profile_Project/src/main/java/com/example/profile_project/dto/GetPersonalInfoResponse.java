package com.example.profile_project.dto;

import com.example.profile_project.entity.MBTI;
import com.example.profile_project.entity.PersonalInfo;

public record GetPersonalInfoResponse(Long id, String name, Double age, Double height, MBTI mbti) {
    public static GetPersonalInfoResponse from(PersonalInfo info) {
        return new GetPersonalInfoResponse(
                info.getId(), info.getName(), info.getAge(),
                info.getHeight(), info.getMbti());
    }
}

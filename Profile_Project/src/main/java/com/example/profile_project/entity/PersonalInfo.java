package com.example.profile_project.entity;

import com.example.profile_project.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double age;

    private Double height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private MBTI mbti;

    public PersonalInfo(String name, Double age, Double height, MBTI mbti) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.mbti = mbti;
    }
}

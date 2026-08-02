package com.example.profile_project.dto;

import com.example.profile_project.entity.MBTI;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreatePersonalInfoRequest(
        @NotBlank(message = "이름이 증발했습니다? 익명 뒤에 숨은 거 보소 ㅋㅋㅋ 주작 없으면 가입을 못 하나요? 👎")
        String name,

        @NotNull(message = "소수점 첫째자리 룰이 우스워? 나이 숨기면 서버가 500살 어르신으로 판단하고 고려장 모드 가동함 🥱")
        @PositiveOrZero(message = "나이를 거꾸로 드셨나요? 시간 여행자면 주식 비트코인 종목 추천이나 하고 노벨상 타러 가세요 🚀")
        @Max(value = 270, message = "270살 초과면 회원가입이 아니라 국가문화유산 등록하고 역사 교과서 검수하러 가셔야 합니다 📜")
        Double age,

        @NotNull(message = "키 누락한 거 보니 100% 성장판 닫힌 키갤러네 ㅋㅋ 서버가 자 들고 쫓아가기 전에 아르기닌이나 마저 드셈 💊")
        @PositiveOrZero(message = "키가 음수? 발이 지하에 묻힌 게 아니라 일리자로프 사지연장술 받다가 부작용 와서 주저앉으신 듯 🦴")
        Double height,

        @NotNull(message = "MBTI 입력 안 하면 서버가 강제로 'SEXY'로 저장해서 데이터베이스에 평생 박제할 예정입니다 🧠")
        MBTI mbti
) {
}

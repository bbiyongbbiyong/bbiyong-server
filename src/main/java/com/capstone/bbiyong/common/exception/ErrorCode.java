package com.capstone.bbiyong.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "C_001", "서버에 문제가 발생하였습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "API는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(400, "ME_001", "사용자를 찾을 수 없습니다."),

    RECORD_NOT_FOUND(400, "RE_001", "기록을 찾을 수 없습니다."),

    WEATHER_NOT_NULL(400, "WE_001", "날씨 정보가 빈 값입니다."),
    WEATHER_NOT_FOUND(400, "WE_002", "날씨 정보를 찾을 수 없습니다."),

    TAG_NOT_FOUND(400, "TA_001", "태그를 찾을 수 없습니다."),

    HEART_NOT_FOUND(400, "HE_001", "좋아요 정보를 찾을 수 없습니다."),
    HEART_DUPLICATE(400, "HE_002", "좋아요는 한 번만 누를 수 있습니다."),

    AUTH_ERROR(400, "AU_001", "기록 작성자만 접근할 수 있습니다."),
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다."),
    UNAUTHORIZED_REDIRECT_URI(400, "AU_003", "인증되지 않은 REDIRECT_URI입니다."),
    BAD_LOGIN(400, "AU_004", "잘못된 아이디 또는 패스워드입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}

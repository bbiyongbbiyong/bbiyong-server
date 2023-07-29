package com.capstone.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "C_001", "서버에 문제가 발생하였습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "API는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),

    LOCATION_NOT_FOUND(400, "L_001", "잘못된 위치 정보입니다. 1~17 사이의 숫자를 입력해주세요."),

    MEMBER_NOT_FOUND(404, "M_001", "사용자를 찾을 수 없습니다."),
    DUPLICATED_USERNAME(409, "M_002", "이미 존재하는 아이디입니다."),
    INVALID_PASSWORD(401, "M_003", "비밀번호가 올바르지 않습니다."),
    INVALID_TOKEN(401, "M_004", "토큰이 만료되었습니다.")
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

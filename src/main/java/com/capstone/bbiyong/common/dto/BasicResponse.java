package com.capstone.bbiyong.common.dto;

import com.capstone.bbiyong.common.exception.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BasicResponse {

    private int status;
    private String message;
    private Object data;

    @Builder
    public BasicResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity<BasicResponse> ok(Object data) {
        BasicResponse basicResponse = new BasicResponse(200, "성공", data);
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    public ResponseEntity<BasicResponse> created(Object data) {
        BasicResponse basicResponse = new BasicResponse(201, "성공", data);
        return new ResponseEntity<>(basicResponse, HttpStatus.CREATED);
    }


    public ResponseEntity<BasicResponse> noContent() {
        BasicResponse basicResponse = new BasicResponse(204, "성공", "");
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    private BasicResponse(ErrorCode code, List<FieldError> errors) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = errors;
    }

    private BasicResponse(ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = new ArrayList<>();
    }

    public static BasicResponse of(ErrorCode code, BindingResult bindingResult) {
        return new BasicResponse(code, FieldError.of(bindingResult));
    }

    public static BasicResponse of(ErrorCode code) {
        return new BasicResponse(code);
    }

    public static BasicResponse of(MethodArgumentTypeMismatchException e) {
        String value = Optional.ofNullable(e.getValue())
                .map(Object::toString)
                .orElse("");

        List<FieldError> errors = FieldError.of(e.getName(), value, e.getErrorCode());
        return new BasicResponse(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(String field, String value, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult
                    .getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" :
                                    error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

}

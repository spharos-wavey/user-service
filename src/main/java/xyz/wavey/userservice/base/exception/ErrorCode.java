package xyz.wavey.userservice.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_USER("회원 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    INVALID_AUTH_TOKEN("권한 정보가 없는 토큰입니다", UNAUTHORIZED, 401),
    INVALID_TOKEN("토큰이 유효하지 않습니다", BAD_REQUEST, 400),
    INVALID_ACCESS("유효하지 않은 접근입니다.", FORBIDDEN, 403),
    ;
    private final String message;
    private final HttpStatus httpStatus;
    private final Integer errorCode;
}

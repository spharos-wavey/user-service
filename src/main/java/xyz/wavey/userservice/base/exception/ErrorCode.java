package xyz.wavey.userservice.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_USER("회원 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    ;
    private final String message;
    private final HttpStatus httpStatus;
    private final Integer errorCode;
}

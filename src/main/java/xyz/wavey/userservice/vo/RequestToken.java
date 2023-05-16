package xyz.wavey.userservice.vo;

import lombok.Data;

@Data
public class RequestToken {

    private String accessToken;
    private String refreshToken;
    private String idToken;

}

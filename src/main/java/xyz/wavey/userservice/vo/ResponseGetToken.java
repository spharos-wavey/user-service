package xyz.wavey.userservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetToken {

    private String accessToken;
    private String refreshToken;
    private String idToken;

}

package xyz.wavey.userservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLogin {

    private String accessToken;

}

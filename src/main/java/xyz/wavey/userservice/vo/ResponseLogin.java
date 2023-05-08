package xyz.wavey.userservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLogin {
    private String email;
    private String name;
    private String ageRange;
    private String phoneNumber;
    private String nickName;
    private String profileImageUrl;
}

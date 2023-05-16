package xyz.wavey.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLogin {
    private String email;
    private String nickName;
    private String profileImageUrl;
}

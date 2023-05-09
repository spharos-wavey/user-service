package xyz.wavey.userservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;
import xyz.wavey.userservice.vo.ResponseGetToken;

public interface OAuthService {
    ResponseGetToken getAccessToken(String code);
    ResponseLogin getUserInfo(String accessToken);
    String decodeToken(String jwt);

    void userValid(ResponseLogin responseLogin, String userId);
    ResponseEntity<Object> login(RequestLogin requestLogin);
}

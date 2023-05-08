package xyz.wavey.userservice.service;

import xyz.wavey.userservice.vo.ResponseLogin;
import xyz.wavey.userservice.vo.ResponseGetToken;

public interface OAuthService {
    ResponseGetToken getAccessToken(String code);
    ResponseLogin getUserInfo(String accessToken);

    String decodeToken(String jwt);
}

package xyz.wavey.userservice.service;

import xyz.wavey.userservice.vo.ResponseGetToken;

import java.util.HashMap;

public interface OAuthService {
    ResponseGetToken getAccessToken(String code);
    HashMap<String,Object> getUserInfo(String accessToken);

    String decodeToken(String jwt);
}

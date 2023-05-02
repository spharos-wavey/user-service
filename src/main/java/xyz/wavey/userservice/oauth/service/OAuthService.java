package xyz.wavey.userservice.oauth.service;

import xyz.wavey.userservice.oauth.vo.ResponseGetToken;

import java.util.HashMap;

public interface OAuthService {
    ResponseGetToken getAccessToken(String code);
    HashMap<String,Object> getUserInfo(String accessToken);
}

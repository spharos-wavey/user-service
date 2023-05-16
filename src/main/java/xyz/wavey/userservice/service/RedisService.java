package xyz.wavey.userservice.service;

import xyz.wavey.userservice.vo.ResponseGetToken;

public interface RedisService {

    void createTokenData(String userId, ResponseGetToken responseGetToken);

    String getAccessToken(String userId);
    String getRefreshToken(String userId);
    void deleteTokenData(String userId);

}

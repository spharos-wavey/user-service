package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.vo.ResponseGetToken;

import java.util.LinkedHashMap;

@Service
@Repository
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, LinkedHashMap<String , String>> redisTemplate;

    public void createTokenData(String userId, ResponseGetToken responseGetToken) {
        ValueOperations<String, LinkedHashMap<String , String>> vop = redisTemplate.opsForValue();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("idToken", responseGetToken.getIdToken());
        map.put("accessToken", responseGetToken.getAccessToken());
        map.put("refreshToken", responseGetToken.getRefreshToken());
        vop.set(userId, map);
    }

    public String getAccessToken(String userId) {
        return redisTemplate.opsForValue().get(userId).get("accessToken");
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get(userId).get("refreshToken");
    }

    public void deleteTokenData(String userId) {
        redisTemplate.delete(userId);
    }

}

package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.OAuthService;
import xyz.wavey.userservice.service.RedisService;
import xyz.wavey.userservice.vo.ResponseGetToken;
import xyz.wavey.userservice.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;
    private final UserService userService;
    private final RedisService redisService;

    @GetMapping("/kakao")
    public ResponseEntity<Object> kakaoLogin(@RequestParam("code") String code){

        ResponseGetToken responseGetToken = oauthService.getAccessToken(code);
        if (responseGetToken != null) {
            HashMap<String,Object> userInfo = oauthService.getUserInfo(responseGetToken.getAccessToken());
            userService.userValid(userInfo.get("email").toString()
                    , userInfo.get("name").toString()
                    , userInfo.get("ageRange").toString()
                    , userInfo.get("phoneNumber").toString()
                    , oauthService.decodeToken(responseGetToken.getIdToken()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer "+responseGetToken.getIdToken());

            // 로그인 완료 시 idToken, accessToken, refreshToken 이 redis 서버에 저장됨
            redisService.createTokenData(oauthService.decodeToken(responseGetToken.getIdToken()), responseGetToken);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/access-token/{userId}")
    public String getAccessToken(@PathVariable String userId) {
        return redisService.getAccessToken(userId);
    }

    @GetMapping("/refresh-token/{userId}")
    public String getRefreshToken(@PathVariable String userId) {
        return redisService.getRefreshToken(userId);
    }
}

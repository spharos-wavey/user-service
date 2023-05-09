package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.OAuthService;
import xyz.wavey.userservice.service.RedisService;
import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;
import xyz.wavey.userservice.vo.ResponseGetToken;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;
    private final RedisService redisService;

    @GetMapping("/kakao")
    public ResponseEntity<Object> kakaoLogin(@RequestParam("code") String code){

        ResponseGetToken responseGetToken = oauthService.getAccessToken(code);
        if (responseGetToken != null) {
            ResponseLogin responseLogin = oauthService.getUserInfo(responseGetToken.getAccessToken());

            String userId = oauthService.decodeToken(responseGetToken.getIdToken());
            oauthService.userValid(responseLogin, userId);

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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin requestLogin){
        //Todo 만약 레디스에 토큰 저장 할 예정이면 -> requestToken 파라미터로 받고 redisService의 createTokenData 메소드 사용
        return oauthService.login(requestLogin);

    }
}

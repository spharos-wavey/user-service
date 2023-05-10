package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.OAuthService;
import xyz.wavey.userservice.vo.RequestLogin;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin requestLogin){
        //Todo 만약 레디스에 토큰 저장 할 예정이면 -> requestToken 파라미터로 받고 redisService의 createTokenData 메소드 사용
        return oauthService.login(requestLogin);

    }
}

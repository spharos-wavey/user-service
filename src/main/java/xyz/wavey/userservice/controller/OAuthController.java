package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.OAuthService;
import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin requestLogin){
        ResponseLogin responseLogin = oauthService.login(requestLogin);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, responseLogin.getAccessToken());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}

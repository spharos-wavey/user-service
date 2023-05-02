package xyz.wavey.userservice.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.oauth.service.OAuthService;
import xyz.wavey.userservice.oauth.vo.ResponseGetToken;
import xyz.wavey.userservice.user.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;
    private final UserService userService;

    @GetMapping("/kakao")
    public ResponseEntity<Object> kakaoLogin(@RequestParam("code") String code){

        ResponseGetToken responseGetToken = oauthService.getAccessToken(code);
        if (responseGetToken != null) {
            HashMap<String,Object> userInfo = oauthService.getUserInfo(responseGetToken.getAccessToken());
            userService.userValid(userInfo.get("email").toString(), userInfo.get("nickName").toString());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer "+responseGetToken.getIdToken());

            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}

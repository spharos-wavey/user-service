package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.UserService;
import xyz.wavey.userservice.vo.RequestLogin;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin requestLogin){
        //Todo 만약 레디스에 토큰 저장 할 예정이면 -> requestToken 파라미터로 받고 redisService의 createTokenData 메소드 사용
        return userService.login(requestLogin);

    }

}

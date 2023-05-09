package xyz.wavey.userservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;

public interface UserService {

    void userValid(ResponseLogin responseLogin, String userId);
    ResponseEntity<Object> login(RequestLogin requestLogin);
}

package xyz.wavey.userservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.userservice.vo.RequestLogin;

public interface OAuthService {

    ResponseEntity<Object> login(RequestLogin requestLogin);

}

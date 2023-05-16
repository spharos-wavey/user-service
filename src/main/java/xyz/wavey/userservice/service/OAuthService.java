package xyz.wavey.userservice.service;

import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;

public interface OAuthService {

    ResponseLogin login(RequestLogin requestLogin);

}

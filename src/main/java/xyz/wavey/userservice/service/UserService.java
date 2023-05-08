package xyz.wavey.userservice.service;

import xyz.wavey.userservice.vo.ResponseLogin;

public interface UserService {

    void userValid(ResponseLogin responseLogin, String userId);

}

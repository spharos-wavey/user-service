package xyz.wavey.userservice.service;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import xyz.wavey.userservice.vo.ResponseLogin;

public interface UserService {

    void userValid(ResponseLogin responseLogin, String userId);

}

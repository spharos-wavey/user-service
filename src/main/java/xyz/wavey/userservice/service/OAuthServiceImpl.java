package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import xyz.wavey.userservice.base.config.JwtService;
import xyz.wavey.userservice.base.exception.ServiceException;
import xyz.wavey.userservice.model.User;
import xyz.wavey.userservice.repository.UserRepo;
import xyz.wavey.userservice.vo.RequestLogin;
import xyz.wavey.userservice.vo.ResponseLogin;
import java.util.UUID;

import static xyz.wavey.userservice.base.exception.ErrorCode.NOT_FOUND_USER;


@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final UserRepo userRepo;

    private final JwtService jwtService;

    public ResponseLogin login(RequestLogin requestLogin) {
        if (Boolean.FALSE.equals(userRepo.existsByEmail(requestLogin.getEmail()))) { //디비에 존재 안하면 회원가입
            UUID uuid = UUID.randomUUID();
            userRepo.save(User.builder()
                    .email(requestLogin.getEmail())
                    .profileImageUrl(requestLogin.getProfileImageUrl())
                    .uuid(uuid.toString())
                    .nickName(requestLogin.getNickName())
                    .build());
        }
        //이미 회원이면 로그인
        User user = userRepo.findByEmail(requestLogin.getEmail()).orElseThrow(()
                -> new ServiceException(NOT_FOUND_USER.getMessage(),NOT_FOUND_USER.getHttpStatus()));
        String accessToken = jwtService.generateToken(user);
        ResponseLogin responseLogin = ResponseLogin.builder()
                .accessToken(accessToken).build();
        return responseLogin;
    }
}

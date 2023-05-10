package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.model.User;
import xyz.wavey.userservice.repository.UserRepo;
import xyz.wavey.userservice.vo.RequestLogin;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final UserRepo userRepo;


    public ResponseEntity<Object> login(RequestLogin requestLogin) {
        if (Boolean.FALSE.equals(userRepo.existsByEmail(requestLogin.getEmail()))) { //디비에 존재 안하면
            UUID uuid = UUID.randomUUID();
            userRepo.save(User.builder()
                    .email(requestLogin.getEmail())
                    .profileImageUrl(requestLogin.getProfileImageUrl())
                    .uuid(uuid.toString())
                    .nickName(requestLogin.getNickName())
                    .build());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

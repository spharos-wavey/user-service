package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.model.User;
import xyz.wavey.userservice.repository.UserRepo;
import xyz.wavey.userservice.vo.ResponseLogin;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    public void userValid(ResponseLogin responseLogin,String userId) {
        if (Boolean.FALSE.equals(userRepo.existsByEmail(responseLogin.getEmail()))){
            userRepo.save(User.builder()
                    .email(responseLogin.getEmail())
                    .profileImageUrl(responseLogin.getProfileImageUrl())
                    .UUID(userId)
                    .nickName(responseLogin.getNickName())
                    .build());
        }
    }
}

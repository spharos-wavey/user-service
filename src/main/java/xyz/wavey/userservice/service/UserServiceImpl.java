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
        char firstAgeRange = responseLogin.getAgeRange().charAt(0);
        if (Boolean.FALSE.equals(userRepo.existsByEmail(responseLogin.getEmail())) && firstAgeRange != '1'){
            userRepo.save(User.builder()
                    .email(responseLogin.getEmail())
                    .name(responseLogin.getName())
                    .phoneNum(responseLogin.getPhoneNumber())
                    .profileImageUrl(responseLogin.getProfileImageUrl())
                    .UUID(userId)
                    .nickName(responseLogin.getNickName())
                    .build());
        }
    }
}

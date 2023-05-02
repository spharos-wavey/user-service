package xyz.wavey.userservice.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.user.model.User;
import xyz.wavey.userservice.user.repository.UserRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    @Override
    public void userValid(String email, String nickName) {
        if (Boolean.FALSE.equals(userRepo.existsByEmail(email))){
            userRepo.save(User.builder()
                    .email(email)
                    .nickName(nickName)
                    .build());
        }
    }
}

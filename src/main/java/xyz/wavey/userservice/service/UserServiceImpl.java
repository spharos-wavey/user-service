package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.model.User;
import xyz.wavey.userservice.repository.UserRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    public void userValid(String email, String nickName, String userId) {
        if (Boolean.FALSE.equals(userRepo.existsByEmail(email))){
            userRepo.save(User.builder()
                    .email(email)
                    .nickName(nickName)
                    .UUID(userId)
                    .build());
        }
    }
}

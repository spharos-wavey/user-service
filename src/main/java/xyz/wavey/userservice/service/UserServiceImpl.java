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

    public void userValid(String email, String name, String ageRange, String phoneNumber, String userId) {
        char firstAgeRange = ageRange.charAt(0);
        if (Boolean.FALSE.equals(userRepo.existsByEmail(email)) && firstAgeRange != '1'){
            userRepo.save(User.builder()
                    .email(email)
                    .name(name)
                    .phoneNum(phoneNumber)
                    .UUID(userId)
                    .build());
        }
    }
}

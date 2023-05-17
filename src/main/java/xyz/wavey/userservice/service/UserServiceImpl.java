package xyz.wavey.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.base.exception.ServiceException;
import xyz.wavey.userservice.repository.UserRepo;

import static xyz.wavey.userservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public ResponseEntity<Object> getUserPk(String uuid) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepo.findByUuid(uuid)
                        .orElseThrow(() -> new ServiceException(NOT_FOUND_USER.getMessage(), NOT_FOUND_USER.getHttpStatus()))
                        .getId());
    }

}

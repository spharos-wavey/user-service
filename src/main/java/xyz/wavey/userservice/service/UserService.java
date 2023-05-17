package xyz.wavey.userservice.service;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> getUserPk(String uuid);

}

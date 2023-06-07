package xyz.wavey.userservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.userservice.vo.ResponseGetReward;

public interface UserService {

    ResponseEntity<Object> getUserPk(String uuid);

    ResponseGetReward getReward(String uuid);

}

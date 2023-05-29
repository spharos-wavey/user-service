package xyz.wavey.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getUserPk(@PathVariable String uuid) {
        return userService.getUserPk(uuid);
    }

    @GetMapping("/reward")
    public ResponseEntity<Object> getReward(@RequestHeader("uid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getReward(uuid));
    }
}

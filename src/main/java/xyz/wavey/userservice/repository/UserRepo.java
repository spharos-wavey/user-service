package xyz.wavey.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.userservice.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByUserId(String userId);

}

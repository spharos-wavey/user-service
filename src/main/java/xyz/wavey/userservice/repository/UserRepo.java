package xyz.wavey.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.userservice.model.User;

public interface UserRepo extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
}

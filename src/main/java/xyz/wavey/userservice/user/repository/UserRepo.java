package xyz.wavey.userservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.userservice.user.model.User;

public interface UserRepo extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
}

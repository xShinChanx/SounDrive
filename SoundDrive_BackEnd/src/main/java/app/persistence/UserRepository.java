package app.persistence;

import app.persistence.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserById(Long id);
    UserEntity findUserByEmail(String email);
    boolean existsByEmail(String email);

}

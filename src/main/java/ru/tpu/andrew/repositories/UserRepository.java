package ru.tpu.andrew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tpu.andrew.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    Optional<User> findByUsernameAndPassword(String username, String password);
}

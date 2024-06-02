package ru.below.identityservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.below.identityservices.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByName(String name);

}

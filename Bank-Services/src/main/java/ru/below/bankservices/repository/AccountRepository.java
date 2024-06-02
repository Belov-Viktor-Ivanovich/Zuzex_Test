package ru.below.bankservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.below.bankservices.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUserId(Long userId);
}

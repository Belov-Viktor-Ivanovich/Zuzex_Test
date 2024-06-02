package ru.below.bankservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.below.bankservices.dto.AccountDTO;
import ru.below.bankservices.entity.Account;
import ru.below.bankservices.repository.AccountRepository;

import java.math.BigDecimal;

public interface AccountService {
    Account getAccount(Long userId);

    Boolean writingOffMoney(AccountDTO accountDTO);

    BigDecimal createAccount(Long userId);

    BigDecimal addBalance(Long id, BigDecimal amount);
}

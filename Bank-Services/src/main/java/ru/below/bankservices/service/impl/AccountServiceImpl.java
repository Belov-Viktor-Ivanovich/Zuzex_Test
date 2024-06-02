package ru.below.bankservices.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.below.bankservices.dto.AccountDTO;
import ru.below.bankservices.entity.Account;
import ru.below.bankservices.kafka.KafkaProducer;
import ru.below.bankservices.repository.AccountRepository;
import ru.below.bankservices.service.AccountService;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final KafkaProducer kafkaProducer;

    @SneakyThrows
    @Override
    @Cacheable
    public Account getAccount(Long userId) {
        return accountRepository.findById(userId).get();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Boolean writingOffMoney(AccountDTO accountDTO) {
            Account account = accountRepository.findAccountByUserId(accountDTO.getUserId()).get();
            var result = account.getBalance().subtract(accountDTO.getAmountOfTheCharge());
            if(result.signum()<0){
                return false;
            }
            account.setBalance(result);
            accountRepository.save(account);
            kafkaProducer.sendMessage("Транзакция прошла успешно");
            return true;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public BigDecimal createAccount(Long userId) {
        var account = Account.builder().userId(userId).balance(BigDecimal.valueOf(999)).build();
        accountRepository.save(account);
        return account.getBalance();
    }

    @Override
    @Transactional
    public BigDecimal addBalance(Long id, BigDecimal amount) {
        var account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account.getBalance();
    }
}

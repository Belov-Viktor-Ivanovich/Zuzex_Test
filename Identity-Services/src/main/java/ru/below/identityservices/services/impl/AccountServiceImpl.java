package ru.below.identityservices.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.below.identityservices.repositories.UserRepository;
import ru.below.identityservices.services.AccountService;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository repository;
    @Autowired
    private final RestTemplate restTemplate;
    @Override
    public BigDecimal getAccountBalance() throws AccessException {
        var user = repository.findUsersByName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new AccessException("user not found"));
        return restTemplate.getForObject("https://STOCK-SERVICES/account/"+user.getId(), BigDecimal.class, user.getId());
    }
}

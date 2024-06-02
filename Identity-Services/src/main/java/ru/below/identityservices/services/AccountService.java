package ru.below.identityservices.services;

import org.springframework.expression.AccessException;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal getAccountBalance() throws AccessException;
}

package ru.below.bankservices.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.below.bankservices.dto.AccountDTO;
import ru.below.bankservices.dto.UserDTO;
import ru.below.bankservices.entity.Account;
import ru.below.bankservices.service.AccountService;
import ru.below.bankservices.service.impl.AccountServiceImpl;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Tag(name = "AccountController", description = "service bank operation")
public class AccountController {
    private final AccountServiceImpl accountService;
    @GetMapping("/{userId}")
    @Operation(description = "Возвращает банковский аккаунт по id пользователя")
    public Account getAccount(@PathVariable Long userId) {
        return accountService.getAccount(userId);
    }
    @PostMapping("/writingOffMoney")
    @Operation(description = "Проверка и списания со счета")
    public Boolean writingOffMoney(@RequestBody AccountDTO accountDTO) {
        return accountService.writingOffMoney(accountDTO);
    }
    @PostMapping("/create")
    @Operation(description = "Создание банковского счета")
    public BigDecimal createAccount(@RequestBody Long userId) {
        return accountService.createAccount(userId);
    }
    @PostMapping("/addBalance/{amount}")
    @Operation(description = "Позволяет пополнять баланс")
    public BigDecimal addBalance(@RequestBody UserDTO userDTO, @PathVariable BigDecimal amount) {
        return accountService.addBalance(userDTO.getId(),amount);
    }
}

package ru.below.bankservices.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountController {
    private final AccountServiceImpl accountService;
    @GetMapping("/{userId}")
    public Account getAccount(@PathVariable Long userId) {
        return accountService.getAccount(userId);
    }
    @PostMapping("/writingOffMoney")
    public Boolean writingOffMoney(@RequestBody AccountDTO accountDTO) {
        return accountService.writingOffMoney(accountDTO);
    }
    @PostMapping("/create")
    public BigDecimal createAccount(@RequestBody Long userId) {
        return accountService.createAccount(userId);
    }
    //переписать чтобы уйти от pathVarible
    @PostMapping("/addBalance/{amount}")
    public BigDecimal addBalance(@RequestBody UserDTO userDTO, @PathVariable BigDecimal amount) {
        return accountService.addBalance(userDTO.getId(),amount);
    }
}

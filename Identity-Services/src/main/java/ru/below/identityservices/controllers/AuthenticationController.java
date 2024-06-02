package ru.below.identityservices.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.below.identityservices.dto.AuthenticationRequest;
import ru.below.identityservices.dto.AuthenticationResponse;
import ru.below.identityservices.dto.RegisterRequest;
import ru.below.identityservices.services.AccountService;
import ru.below.identityservices.services.impl.AuthenticationServiceImpl;
import ru.below.identityservices.services.impl.JwtServiceImpl;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "AuthenticationController", description = "controller authentication and registration")
public class AuthenticationController {
    private final AuthenticationServiceImpl service;
    private final AccountService accountService;

    @PostMapping("/register")
    @Operation(description = "Позволяет зарегистрироваться пользователю")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(description = "Позволяет авторизоваться пользователю")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/validate/{token}")
    @Operation(description = "Позволяет проверить валидность токена")
    public String validateToken(@PathVariable("token") String token) {
        return service.validdate(token)?"is valid":"not is valid";
    }

    @GetMapping("/balance")
    @Operation(description = "позволяет создать банковский аккаунт пользователю")
    public BigDecimal getAccountBalance() throws AccessException {
        return accountService.getAccountBalance();
    }
    @GetMapping("/getUserId/{userName}")
    @Operation(description = "Позволяет получить id пользователя по уникальному логину")
    public Long getUserId(@PathVariable String userName){
        return service.getUserId(userName);
    }

}

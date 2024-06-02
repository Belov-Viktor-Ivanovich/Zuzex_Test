package ru.below.identityservices.controllers;


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
public class AuthenticationController {
    private final AuthenticationServiceImpl service;
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable("token") String token) {
        return service.validdate(token)?"is valid":"not is valid";
    }

    @GetMapping("/balance")
    public BigDecimal getAccountBalance() throws AccessException {
        return accountService.getAccountBalance();
    }
    @GetMapping("/getUserId/{userName}")
    public Long getUserId(@PathVariable String userName){
        return service.getUserId(userName);
    }

}

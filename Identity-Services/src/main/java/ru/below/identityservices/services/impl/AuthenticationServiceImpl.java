package ru.below.identityservices.services.impl;




import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.below.identityservices.configs.JwtAuthenticationFilter;
import ru.below.identityservices.dto.AuthenticationRequest;
import ru.below.identityservices.dto.AuthenticationResponse;
import ru.below.identityservices.dto.RegisterRequest;
import ru.below.identityservices.models.User;
import ru.below.identityservices.repositories.UserRepository;
import ru.below.identityservices.services.AuthenticationService;

import java.math.BigDecimal;
import java.util.Enumeration;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private HttpServletRequest request;



    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);

        var jwtToken = jwtServiceImpl.generateToken(user);
        var res = restTemplate.postForObject("http://BANK-SERVICES/account/create",user.getId(), BigDecimal.class);
        System.out.println(res);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getName());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        var user = repository.findUsersByName(request.getName())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var jwtToken = jwtServiceImpl.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Override
    public boolean validdate(String token) {
        /*System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                System.out.println("Header: " + request.getHeader(headerNames.nextElement()));
            }
        }*/
        return jwtServiceImpl.validdate(token);
    }

    @Override
    public Long getUserId(String userName) {
        return repository.findUsersByName(userName).get().getId();
    }


}

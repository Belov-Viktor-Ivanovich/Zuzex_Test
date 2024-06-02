package ru.below.identityservices.services;


import ru.below.identityservices.dto.AuthenticationRequest;
import ru.below.identityservices.dto.AuthenticationResponse;
import ru.below.identityservices.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
    public boolean validdate(String token);

    Long getUserId(String userName) throws NoSuchFieldException;

}

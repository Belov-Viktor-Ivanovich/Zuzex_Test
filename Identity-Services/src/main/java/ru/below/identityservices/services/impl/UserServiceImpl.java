package ru.below.identityservices.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.AccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.below.identityservices.models.User;
import ru.below.identityservices.repositories.UserRepository;
import ru.below.identityservices.services.UserService;


import java.util.Comparator;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUsersByName(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


}

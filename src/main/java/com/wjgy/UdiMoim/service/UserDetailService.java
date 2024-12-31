package com.wjgy.UdiMoim.service;

import com.wjgy.UdiMoim.domain.User;
import com.wjgy.UdiMoim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException((userId)));
    }
}

package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.entity.AppUser;
import com.enigmacamp.enigma_loan_app.entity.UserRole;
import com.enigmacamp.enigma_loan_app.repository.AppUserRepository;
import com.enigmacamp.enigma_loan_app.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        List<UserRole> userRoles = userRoleService.getByUser(user);
        List<SimpleGrantedAuthority> authorities = userRoles.stream().map(userRole -> {
            String role = userRole.getRole().getRole().name();
            return new SimpleGrantedAuthority(role);
        }).toList();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}

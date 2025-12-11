package com.utkarshshaning.journalApp.service;

import com.utkarshshaning.journalApp.entity.UserEntry;
import com.utkarshshaning.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntryRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPasssword())
                .roles(user.getRoles() != null ? user.getRoles().toArray(new String[0]) : new String[]{"USER"})
                .build();
    }
}
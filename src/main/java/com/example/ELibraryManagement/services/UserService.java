package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.Authority;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findById(username).orElse(null);
    }

    public User createUser(User user, Authority authority) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAuthorities(authority);
        return this.userRepository.save(user);
    }

}

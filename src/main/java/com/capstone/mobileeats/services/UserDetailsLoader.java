package com.capstone.mobileeats.services;

import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.models.UserWithRoles;
import com.capstone.mobileeats.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = users.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for email " + email);
        }

        return new UserWithRoles(user);
    }
}
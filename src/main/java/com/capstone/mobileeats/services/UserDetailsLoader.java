package com.capstone.mobileeats.services;


import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.models.UserWithRoles;
import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorWithRoles;

import com.capstone.mobileeats.repositories.UserRepository;

import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;
    private final VendorRepository vendors;

    public UserDetailsLoader(UserRepository users, VendorRepository vendors) {
        this.users = users;
        this.vendors = vendors;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = users.findByEmail(email);
        if (user == null) {
            Vendor vendor = vendors.findByEmail(email);
            if (user == null && vendor == null) {
                throw new UsernameNotFoundException("No profile found for email " + email);
            }
            return new VendorWithRoles(vendor);
        }
        return new UserWithRoles(user);
    }
}
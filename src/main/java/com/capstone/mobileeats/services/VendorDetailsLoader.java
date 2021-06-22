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
public class VendorDetailsLoader implements UserDetailsService {
    private final VendorRepository vendors;

    public VendorDetailsLoader(VendorRepository vendors) {
        this.vendors = vendors;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Vendor vendor = vendors.findByEmail(email);
        if (vendor == null) {
            throw new UsernameNotFoundException("No vendor found for email " + email);
        }

        return new VendorWithRoles(vendor);
    }
}
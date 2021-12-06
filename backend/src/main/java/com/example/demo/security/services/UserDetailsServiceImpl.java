package com.example.demo.security.services;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        userRepository.save(user);
        return UserDetailsImpl.build(user);
    }
}

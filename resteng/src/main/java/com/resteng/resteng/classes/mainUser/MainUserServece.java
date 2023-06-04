package com.resteng.resteng.classes.mainUser;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.security.MainUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainUserServece implements UserDetailsService {

    MainUserRepo mainUserRepo;
    PasswordEncoder passwordEncoder;

    public List<MainUser> getAllUser() {
        return mainUserRepo.findAll();
    }

    public MainUser getUserById(long id) {
        return mainUserRepo.findById(id).orElse(null);
    }

    public MainUser newUser(MainUser user) {
        user.setUser_password(passwordEncoder.encode(user.getUser_password()));
        MainUser newUser = mainUserRepo.save(user);
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MainUser> user = mainUserRepo.findUserByUsername(username);

        if (!user.isPresent()) {

            throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
        }

        Set<GrantedAuthority> roles = new HashSet<>();

        roles.add(new SimpleGrantedAuthority(user.get().getRole().getName()));

        return new MainUserDetails(user.get());
    }

    public long getUserRoleId(String username) {
        return mainUserRepo.findUserByUsername(username).get().getRole().getId();
    }

}

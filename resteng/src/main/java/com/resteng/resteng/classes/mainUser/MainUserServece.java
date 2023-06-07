package com.resteng.resteng.classes.mainUser;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.entity.RoleRepo;
import com.resteng.resteng.classes.security.MainUserDetails;
import com.resteng.resteng.classes.supplier.Supplier;
import com.resteng.resteng.classes.supplier.SupplierRepository;
import com.resteng.resteng.classes.user.AppUser;
import com.resteng.resteng.classes.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainUserServece implements UserDetailsService {

    MainUserRepo mainUserRepo;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    SupplierRepository supplierRepository;
    RoleRepo roleRepo;

    public List<MainUser> getAllUser() {
        return mainUserRepo.findAll();
    }

    public MainUser getUserById(long id) {
        return mainUserRepo.findById(id).orElse(null);
    }

    public MainUser getUserByUserName(String username) {
        return mainUserRepo.findUserByUsername(username).orElse(null);
    }

    public MainUser newUser(MainUser user, String role) {
        user.setUser_password(passwordEncoder.encode(user.getUser_password()));
        user.setRole(roleRepo.findByName(role));
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

    AppUser getMainUserByUserName(String username) {
        MainUser mainUser = this.getUserByUserName(username);
        List<AppUser> users = userRepository.findAll().stream()
                .filter(e -> e.getMainUser().getId() == mainUser.getId())
                .collect(Collectors.toList());
        return users.get(0);

    }

    Supplier getSuppByUserName(String username) {
        MainUser mainUser = this.getUserByUserName(username);
        List<Supplier> suppliers = supplierRepository.findAll().stream()
                .filter(e -> e.getMainUser().getId() == mainUser.getId())
                .collect(Collectors.toList());
        return suppliers.get(0);
    }
}

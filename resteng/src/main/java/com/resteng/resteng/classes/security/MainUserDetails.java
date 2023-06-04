package com.resteng.resteng.classes.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.resteng.resteng.classes.entity.Role;
import com.resteng.resteng.classes.mainUser.MainUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainUserDetails implements UserDetails {

    private Long id;
    private String user_password;
    private String username;
    Role role;

    public MainUserDetails() {
        super();
    }

    public MainUserDetails(MainUser mainUser) {
        super();
        this.user_password = mainUser.getUser_password();
        this.username = mainUser.getUsername();
        this.role = mainUser.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getName()));
        return roles;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

package com.resteng.resteng.classes.mainUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.resteng.resteng.classes.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Table(name = "MainUser")
@Entity
@Getter
@Setter
public class MainUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "main_user_password")
    private String user_password;

    @Column(name = "main_user_username", unique = true)
    private String username;

    @OneToOne
    Role role;

    private boolean isEnabled;

    private boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    private boolean isAccountNonExpired;

    public MainUser() {
    }

    public MainUser(@NotNull @NotBlank String user_password, @NotNull @NotBlank String username) {
        this.user_password = user_password;
        this.username = username;
    }

    public MainUser(Long id) {
        super();
        this.id = id;
    }

}

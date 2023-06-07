package com.resteng.resteng.classes.mainUser;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainUserDto {

    @NotNull
    @NotBlank
    private String user_password;

    @NotNull
    @NotBlank
    private String username;

    @OneToOne
    String role;
}

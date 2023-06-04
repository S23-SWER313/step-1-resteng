package com.resteng.resteng.classes.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.resteng.resteng.classes.entity.Role;
import com.resteng.resteng.classes.entity.RoleService;
import com.resteng.resteng.classes.mainUser.MainUser;
import com.resteng.resteng.classes.mainUser.MainUserServece;
import com.resteng.resteng.classes.supplier.Supplier;
import com.resteng.resteng.classes.supplier.SupplierService;
import com.resteng.resteng.classes.user.AppUser;
import com.resteng.resteng.classes.user.MainService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {

    private final MainUserServece mainUserServece;

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        if (roleService.findAllRoles().isEmpty()) {
            roleService.save(new Role("costumer"));
            roleService.save(new Role("supplier"));
        }

        if (mainUserServece.getAllUser().isEmpty()) {// Assuming the Role constructor accepts a String parameter
            MainUser user = new MainUser("password123", "john_doe", roleService.findRoleByName("costumer"));
            MainUser user2 = new MainUser("123", "moh", roleService.findRoleByName("supplier"));

            mainUserServece.newUser(user);
            mainUserServece.newUser(user2);
        }

    }

}
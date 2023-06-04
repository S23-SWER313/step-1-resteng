package com.resteng.resteng.classes.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    public Role findRoleById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }

    public Role findRoleByName(String name) {
        return roleRepo.findByName(name);
    }

    public void save(Role role) {
        roleRepo.save(role);
    }
}

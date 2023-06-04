package com.resteng.resteng.classes.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        Iterable<Role> users = roleService.findAllRoles();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = { "{id}", "/{id}" })
    public ResponseEntity<Role> getRoleById(@PathVariable long id) {
        Role r = roleService.findRoleById(id);
        if (r != null)
            return new ResponseEntity<>(r, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

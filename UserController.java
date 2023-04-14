package com.osama.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
class UserController {

  private final UserRepository repository;
  private final UserModelAssembler assembler;

  UserController(UserRepository repository, UserModelAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {

    List<EntityModel<User>> users = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody User newUser) {

    EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  // Single item

  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable Integer id) {

    User user = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(user);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

    User updatedUser = repository.findById(id) //
        .map(user -> {
          user.setUser_first_name(newUser.getUser_first_name());
          user.setUser_last_name(newUser.getUser_last_name());
          user.setUser_email(newUser.getUser_email());
          user.setUser_country(newUser.getUser_country());
          user.setUser_state(newUser.getUser_state());
          user.setUser_city(newUser.getUser_city());
          user.setUser_address1(newUser.getUser_address1());
          user.setUser_address2(newUser.getUser_address2());
          user.setUser_phone(newUser.getUser_phone());
          return repository.save(user);
        }) //
        .orElseGet(() -> {
          newUser.setUser_id(id); 
          return repository.save(newUser);
        });

    EntityModel<User> entityModel = assembler.toModel(updatedUser);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  @DeleteMapping("/users/{id}")
  ResponseEntity<?> deleteUser(@PathVariable Integer id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}

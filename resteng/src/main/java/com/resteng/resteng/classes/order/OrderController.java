package com.resteng.resteng.classes.order;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = { "users/{userId}/products/{productId}", "/users/{userId}/products/{productId}" })
    public ResponseEntity<CostomerOrder> CreateNewOrder(@Valid @RequestBody CostomerOrder order,
            @PathVariable Long userId,
            @PathVariable Long productId) {
        CostomerOrder newOrder = orderService.createOrder(order, userId, productId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newOrder.getOrder_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}

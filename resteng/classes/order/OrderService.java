package com.resteng.resteng.classes.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setQuantity(updatedOrder.getQuantity());
            order.setOrder_date(updatedOrder.getOrder_date());
            return orderRepository.save(order);
        } else {
            updatedOrder.setOrder_id(id);
            return orderRepository.save(updatedOrder);
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}


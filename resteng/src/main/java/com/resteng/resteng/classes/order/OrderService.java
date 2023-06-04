package com.resteng.resteng.classes.order;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.order_product.Order_product;
import com.resteng.resteng.classes.order_product.Order_product_repo;
import com.resteng.resteng.classes.products.Product;
import com.resteng.resteng.classes.products.ProductRepo;
import com.resteng.resteng.classes.user.AppUser;
import com.resteng.resteng.classes.user.UserRepository;

@Service
public class OrderService {
    OrderRepository orderRepository;
    Order_product_repo order_product_repo;
    UserRepository userRepository;
    ProductRepo productRepo;

    public OrderService(OrderRepository orderRepository, Order_product_repo order_product_repo,
            UserRepository userRepository, ProductRepo productRepo) {
        this.orderRepository = orderRepository;
        this.order_product_repo = order_product_repo;
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }

    public CostomerOrder createOrder(CostomerOrder order, Long userId, Long productId) {
        LocalDate today = LocalDate.now();
        CostomerOrder newOrder = new CostomerOrder(order.getQuantity(), today);
        AppUser user = userRepository.findById(userId).get();
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        Product product = productRepo.findById(productId).get();
        Order_product order_product = new Order_product(newOrder, product);
        order_product_repo.save(order_product);
        return newOrder;
    }

}

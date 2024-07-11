package com.example.undiciluglio.services;

import com.example.undiciluglio.entities.Order;
import com.example.undiciluglio.repos.OrderRepo;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }
    @Transactional
    public List<Order> findAllWithProducts() {
        List<Order> orders = findAll();
        for (Order order : orders) {
            Hibernate.initialize(order.getProducts());
        }
        return orders;
    }

    public Order getById(Integer id) {
        Optional<Order> foundOrder = orderRepo.findById(id);
        return foundOrder.orElse(null);
    }

    public Order update(Integer id, Order order) {
        Optional<Order> foundOrder = orderRepo.findById(id);
        if (foundOrder.isPresent()) {
            Order existingOrder = foundOrder.get();
            existingOrder.setProducts(order.getProducts());
            return orderRepo.save(existingOrder);
        }
        return null;
    }

    public void delete(Integer id) {
        Optional<Order> foundOrder = orderRepo.findById(id);
        foundOrder.ifPresent(orderRepo::delete);
    }
}

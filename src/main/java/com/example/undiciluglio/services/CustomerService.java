package com.example.undiciluglio.services;

import com.example.undiciluglio.entities.Customer;
import com.example.undiciluglio.entities.Order;
import com.example.undiciluglio.repos.CustomerRepo;
import com.example.undiciluglio.repos.OrderRepo;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getById(Integer id) {
        Optional<Customer> foundCustomer = customerRepo.findById(id);
        return foundCustomer.orElse(null);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    @Transactional
    public List<Customer> findAllWithOrders() {
        List<Customer> customers = findAll();
        for (Customer customer : customers) {
            Hibernate.initialize(customer.getOrders());
        }
        return customers;
    }

    public Customer update(Customer customer, Integer id) {
        Optional<Customer> foundCustomer = customerRepo.findById(id);
        if (foundCustomer.isPresent()) {
            Customer existingCustomer = foundCustomer.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            return customerRepo.save(existingCustomer);
        }
        return null;
    }

    public void delete(Integer id) {
        Optional<Customer> foundCustomer = customerRepo.findById(id);
        foundCustomer.ifPresent(customerRepo::delete);
    }

    @Transactional
    public void removeOrder(Order order, Customer customer) {
        customer.removeOrder(order);
        customerRepo.save(customer);
    }
}

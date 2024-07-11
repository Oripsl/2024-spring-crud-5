package com.example.undiciluglio.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "ordine")
@Table(name = "ordini")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_products")
    private List<Product> products = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products = products.stream().filter(product1 -> !Objects.equals(product1.getId(), product.getId())).toList();
    }

    public int getFullPrice(boolean includeVat) {
        return products.stream()
                .mapToInt(product -> product.getFullPrice(includeVat))
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}

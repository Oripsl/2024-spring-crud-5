package com.example.undiciluglio.services;

import com.example.undiciluglio.entities.Product;
import com.example.undiciluglio.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }
}

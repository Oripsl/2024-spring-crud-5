package com.example.undiciluglio.entities;

import jakarta.persistence.*;

@Entity(name = "product")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int price;

    private int vat;

    public Product() {}

    public Product(String name, int price, int vat) {
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public int getFullPrice(boolean includeVat) {
        if (includeVat) {
            return price + (price * vat / 100);
        } else {
            return price;
        }
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vat=" + vat +
                '}';
    }
}

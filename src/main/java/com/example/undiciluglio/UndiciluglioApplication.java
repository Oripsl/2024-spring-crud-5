package com.example.undiciluglio;

import com.example.undiciluglio.entities.Customer;
import com.example.undiciluglio.entities.Order;
import com.example.undiciluglio.entities.Product;
import com.example.undiciluglio.services.CustomerService;
import com.example.undiciluglio.services.OrderService;
import com.example.undiciluglio.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UndiciluglioApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(UndiciluglioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer1 = new Customer("Mario", "Rossi", "mario.rossi@example.com", "1234567890");
		Customer customer2 = new Customer("Piero", "aa", "aaaaa", "aaaaaa");
		customerService.save(customer1);
		customerService.save(customer2);

		Product product1 = new Product("Laptop", 1000, 22);
		Product product2 = new Product("Mouse", 50, 22);
		productService.save(product1);
		productService.save(product2);

		Order order1 = new Order(customer1);
		Order order2 = new Order(customer2);
		order1.addProduct(product1);
		order2.addProduct(product2);
		orderService.save(order1);
		orderService.save(order2);

		List<Order> orders = orderService.findAllWithProducts();
		System.out.println(orders);
		Order order = orders.getFirst();
		System.out.println("//////////////////////////////////");
		System.out.println(order.getProducts());

		System.out.println("//////////////////////////////////");

		List<Customer> customers = customerService.findAllWithOrders();
		Customer customer = customers.getFirst();
		customerService.removeOrder(customer.getOrders().getFirst(), customer);

		System.out.println(customer.getOrders());
		}
}

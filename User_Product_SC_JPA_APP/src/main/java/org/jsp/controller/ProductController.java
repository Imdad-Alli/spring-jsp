package org.jsp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.configuration.AppConfig;
import org.jsp.dao.ProductDao;
import org.jsp.dao.UserDao;
import org.jsp.dto.Product;
import org.jsp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
	static Scanner sc = new Scanner(System.in);
	static ProductDao productDao;

	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		productDao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Add product");
		System.out.println("2.Find products By user id ");
		System.out.println("3.Find products By user Phone And Password ");
		System.out.println("4.Find products By Brand ");
		System.out.println("5.Find products By Category ");

		System.out.println("Enter Choice: ");

		switch (sc.nextInt()) {
		case 1: {
			saveProduct();
			break;
		}
		case 2: {
			findProductsByuserId();
			break;
		}
		case 3: {
			findProductsByuserPhonePassword();
			break;
		}
		case 4: {
			findProductsByBrand();
			break;
		}
		case 5: {
			findProductsByCategory();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void saveProduct() {
		System.out.println("Enter the name, description, brand, category and cost to save product");
		Product product = new Product();

		product.setName(sc.next());
		product.setDescription(sc.next());
		product.setBrand(sc.next());
		product.setCategory(sc.next());
		product.setCost(sc.nextDouble());

		System.out.println("Enter user id to associate with user: ");
		Integer id = sc.nextInt();

		boolean response = productDao.saveProduct(product, id);

		if (response) {
			System.out.println("Product registered with Id:" + product.getId());
		} else {
			System.out.println("Failed To Save Product");
		}
	}

	public static void findProductsByuserId() {
		System.out.println("Enter user id: ");
		int id = sc.nextInt();

		List<Product> products = productDao.findProducts(id);

		for (Product prod : products) {
			System.out.println(prod);
		}
	}

	public static void findProductsByuserPhonePassword() {
		System.out.println("Enter user phone and password: ");
		Long phone = sc.nextLong();
		String password = sc.next();
		
		List<Product> products = productDao.findProducts(phone, password);
		
		for (Product prod : products) {
			System.out.println(prod);
		}
	}
	
	public static void findProductsByBrand() {
		System.out.println("Enter Product brand: ");
		String brand = sc.next();

		List<Product> products = productDao.findProducts(brand);

		for (Product prod : products) {
			System.out.println(prod);
		}
	}

	public static void findProductsByCategory() {
		System.out.println("Enter Product Category: ");
		String category = sc.next();
		
		List<Product> products = productDao.findProductsByCategory(category);
		
		for (Product prod : products) {
			System.out.println(prod);
		}
	}
}

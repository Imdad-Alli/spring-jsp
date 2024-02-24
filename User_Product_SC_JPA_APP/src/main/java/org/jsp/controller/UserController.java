package org.jsp.controller;

import java.util.Scanner;

import org.jsp.configuration.AppConfig;
import org.jsp.dao.UserDao;
import org.jsp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	static Scanner sc = new Scanner(System.in);
	static UserDao userDao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		userDao = context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Save user");
		System.out.println("2.Update User");
		System.out.println("3.Verify user By Phone and password");
		System.out.println("4.Verify user By Email and password");
		System.out.println("5.Find User By Id");
		System.out.println("6.Find User By Product Id");
		

		System.out.println("Enter Choice: ");

		switch (sc.nextInt()) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			updateUser();
			break;
		}
		case 3: {
			verifyByPhonePassword();
			break;
		}
		case 4: {
			verifyByEmailPassword();
			break;
		}
		case 5: {
			findUserById();
			break;
		}
		case 6: {
			findUserByProductId();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void saveUser() {
		System.out.println("Enter the name,phone, email and password to save user");
		User user = new User();
		user.setName(sc.next());
		user.setPhone(sc.nextLong());
		user.setEmail(sc.next());
		user.setPassword(sc.next());

		boolean response = userDao.saveUser(user);

		if (response) {
			System.out.println("User registered with Id:" + user.getId());
		} else {
			System.out.println("Failed To Save User");
		}
	}

	public static void updateUser() {
		System.out.println("Enter the User Id to update");
		int id = sc.nextInt();
		System.out.println("Enter the name, phone, email and password to save user");
		User user = new User();
		user.setId(id);
		user.setName(sc.next());
		user.setPhone(sc.nextLong());
		user.setEmail(sc.next());
		user.setPassword(sc.next());

		boolean response = userDao.updateUser(user);

		if (response)
			System.out.println("User updated");
		else
			System.err.println("cannot update user as the entered id is Invalid");
	}

	public static void verifyByPhonePassword() {
		System.out.println("Enter the Phone Number and password to verify User");
		long phone = sc.nextLong();
		String password = sc.next();
		User user = userDao.verify(phone, password);

		if (user != null) {
			System.out.println("User Verified Succesfully");
			System.out.println(user);
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void verifyByEmailPassword() {
		System.out.println("Enter the Email Id and password to verify User");
		String email = sc.next();
		String password = sc.next();
		User user = userDao.verify(email, password);
		
		if (user != null) {
			System.out.println("User Verified Succesfully");
			System.out.println(user);
		} else {
			System.err.println("Invalid Email Id or Password");
		}
	}
	
	public static void findUserById() {
		System.out.println("Enter user id to fetch user: ");
		int id = sc.nextInt();
		
		User user = userDao.findUser(id);
		
		if(user != null) {
			System.out.println(user);
		} else {
			System.out.println("Invalid user id");
		}
	}

	public static void findUserByProductId() {
		System.out.println("Enter product id to fetch user: ");
		int id = sc.nextInt();
		
		User user = userDao.findUserByProductId(id);
		
		if(user != null) {
			System.out.println(user);
		} else {
			System.out.println("Invalid user id");
		}
	}

}

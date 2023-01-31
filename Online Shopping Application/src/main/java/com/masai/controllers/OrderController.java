/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.Order;
import com.masai.model.User;
import com.masai.services.OrderService;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrderHandler(@RequestParam String key)
			throws LoginException, CustomerException, OrderException, CartException, ProductException {

		Order order = orderService.addOrder(key);

		return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteOrder")
	public ResponseEntity<Order> removeOrderHandler(@Valid @RequestParam Integer order_Id, @RequestParam String key,
			@Valid @RequestBody User user) throws OrderException, LoginException, CustomerException, UserException {

		Order order = orderService.removeOrder(order_Id, key, user);

		return new ResponseEntity<Order>(order, HttpStatus.OK);

	}

	@GetMapping("/viewOrder")
	public ResponseEntity<List<Order>> viewOrderHandler(@RequestParam String key)
			throws LoginException, CustomerException, OrderException {

		List<Order> listOfOrders = orderService.viewOrder(key);

		return new ResponseEntity<List<Order>>(listOfOrders, HttpStatus.OK);
	}

	@PostMapping("/allOrders")
	public ResponseEntity<List<Order>> viewallOrdersByDateHandler(@RequestParam String key,
			@Valid @RequestParam String stringdate) throws OrderException, CustomerException, LoginException {

		// Please Make Sure Date should be in this format "YYYY-MM-DD"

		List<Order> listOfOrders = orderService.viewallOrdersByDate(key, stringdate);

		return new ResponseEntity<List<Order>>(listOfOrders, HttpStatus.OK);

	}

	@PostMapping("/viewLocationOrders")
	public ResponseEntity<List<Order>> viewAllOrdersByLocationHandler(@RequestParam String key,
			@Valid @RequestParam String location, @Valid @RequestParam String userId,@Valid @RequestParam String userPassword )
			throws OrderException, LoginException, CustomerException, AdminException, UserException {

		List<Order> listoforders = orderService.viewAllOrdersByLocation(key, location, userId,userPassword);

		return new ResponseEntity<List<Order>>(listoforders, HttpStatus.OK);

	}

	@PostMapping("/viewUserOrder")
	public ResponseEntity<List<Order>> viewAllOrdersbyUserIdHandler(@RequestParam String key,
			@Valid @RequestParam String userId,@Valid @RequestParam String userPassword , @Valid @RequestParam String customer_userId)
			throws OrderException, UserException, LoginException, CustomerException, AdminException {

		List<Order> listoforders = orderService.viewAllOrdersbyUserId(userId,userPassword, key, customer_userId);

		return new ResponseEntity<List<Order>>(listoforders, HttpStatus.OK);

	}

}

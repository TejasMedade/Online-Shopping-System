/**
 * 
 */
package com.masai.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.UserException;
import com.masai.model.Order;
import com.masai.model.User;
import com.masai.services.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
			throws LoginException, CustomerException, OrderException, CartException {

		Order order = orderService.addOrder(key);

		return new ResponseEntity(order, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteOrder")
	public ResponseEntity<String> removeOrderHandler(@RequestParam Integer order_Id, @RequestParam String key)
			throws OrderException, LoginException, CustomerException {

		String result = orderService.removeOrder(order_Id, key);

		return new ResponseEntity(result, HttpStatus.OK);

	}

	@GetMapping("/viewOrder")
	public ResponseEntity<List<Order>> viewOrderHandler(@RequestParam String key)
			throws LoginException, CustomerException, OrderException {

		List<Order> listOfOrders = orderService.viewOrder(key);

		return new ResponseEntity(listOfOrders, HttpStatus.OK);
	}

	@PostMapping("/allOrders")
	public ResponseEntity<List<Order>> viewallOrdersByDateHandler(@RequestParam String key, @RequestParam LocalDate date)
			throws OrderException, CustomerException, LoginException {

		List<Order> listOfOrders = orderService.viewOrder(key);

		return new ResponseEntity(listOfOrders, HttpStatus.OK);

	}

	@GetMapping("/viewLocationOrders")
	public ResponseEntity<List<Order>> viewAllOrdersByLocationHandler(@RequestParam String key, @RequestParam String location)
			throws OrderException, LoginException, CustomerException {

		List<Order> listoforders = orderService.viewAllOrdersByLocation(key, location);

		return new ResponseEntity(listoforders, HttpStatus.OK);

	}

	@GetMapping("/viewUserOrder")
	public ResponseEntity<List<Order>> viewAllOrdersbyUserIdHandler(@RequestBody User user, @RequestParam String key)
			throws OrderException, UserException, LoginException, CustomerException {

		List<Order> listoforders = orderService.viewAllOrdersbyUserId(user, key);

		return new ResponseEntity(listoforders, HttpStatus.OK);

	}

}

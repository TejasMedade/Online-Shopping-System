/**
 * 
 */
package com.masai.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.UserException;
import com.masai.model.Order;
import com.masai.model.User;

/**
 * @author tejas
 *
 */

public interface OrderService {

	public Order addOrder(String key) throws LoginException, CustomerException, OrderException, CartException;

	public String removeOrder(Integer order_Id, String key) throws OrderException, LoginException, CustomerException;

	public List<Order> viewOrder(String key) throws LoginException, CustomerException, OrderException;

	public List<Order> viewallOrdersByDate(String key, LocalDate date)
			throws OrderException, CustomerException, LoginException;

	public List<Order> viewAllOrdersByLocation(String key, String location)
			throws OrderException, LoginException, CustomerException;

	List<Order> viewAllOrdersbyUserId(User user, String key)
			throws OrderException, UserException, LoginException, CustomerException;
}

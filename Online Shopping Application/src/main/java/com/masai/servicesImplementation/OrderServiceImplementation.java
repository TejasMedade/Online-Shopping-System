/**
 * 
 */
package com.masai.servicesImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ProductDTO;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.UserException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.model.User;
import com.masai.repository.CartRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.OrderRepo;
import com.masai.services.LoginLogoutService;
import com.masai.services.OrderService;

/**
 * @author tejas
 *
 */

//{
//	  "currentSessionId": 4,
//	  "customerId": 1,
//	  "key": "LMN1pJ",
//	  "localDateTime": "2022-11-16T22:33:39.2100977"
//	}

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private LoginLogoutService loginLogoutServiceImplementation;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepo cartRepo;

	@Override
	public List<Order> viewAllOrdersbyUserId(User user, String key)
			throws OrderException, UserException, LoginException, CustomerException {

		User validate_user = loginLogoutServiceImplementation.validateUser(user, key);

		if (validate_user != null) {

			Optional<Customer> optional_customer = customerRepo.findByMobileNumber(user.getId());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				List<Order> listoforders = customer.getListOfOrders();

				if (!listoforders.isEmpty()) {

					return listoforders;

				} else {
					throw new OrderException("No Orders Are Been Placed With The Customer_Id : " + user.getId());
				}
			} else {
				throw new CustomerException("No Customer Registered With The User Id : " + user.getId());
			}

		} else {
			throw new UserException("User Authentication Failed, Please Login In !");
		}

	}

	@Override
	public String removeOrder(Integer orderId, String key) throws OrderException, LoginException, CustomerException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Order> optional_order = orderRepo.findById(orderId);

			if (optional_order.isPresent()) {

				orderRepo.deleteById(orderId);

				return "Order Deleted Successfully !";
			} else {
				throw new OrderException("No Orders Are Found With This Order_Id : " + orderId);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public List<Order> viewallOrdersByDate(String key, LocalDate date)
			throws OrderException, CustomerException, LoginException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			List<Order> listOfOrdersByLocalDate = orderRepo.findByorderDate(date);

			if (!listOfOrdersByLocalDate.isEmpty()) {

				return listOfOrdersByLocalDate;
			} else {
				throw new OrderException("No Orders Found For This Date : " + date);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public List<Order> viewAllOrdersByLocation(String key, String location)
			throws OrderException, LoginException, CustomerException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			List<Order> listOfOrdersByLocation = orderRepo.findBylocation(location);

			if (!listOfOrdersByLocation.isEmpty()) {

				return listOfOrdersByLocation;

			} else {
				throw new OrderException("No Orders Found For This Location : " + location);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public Order addOrder(String key) throws LoginException, CustomerException, OrderException, CartException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (!optional_cart.isEmpty()) {

				Cart cart = optional_cart.get();

				Order order = new Order();

				order.setCustomer(customer);
				order.setAddress(customer.getAddress());
				order.setLocation(customer.getAddress().getCity());
				order.setOrderDate(LocalDateTime.now());
				order.setOrderStatus("Order Confirmed");

				List<ProductDTO> listofcartproducts = cart.getProducts();

				if (!listofcartproducts.isEmpty()) {

					Double totalprice = 0.0;

					List<ProductDTO> listoforderedproducts = new ArrayList<>();

					for (ProductDTO p : listofcartproducts) {

						listoforderedproducts.add(p);

						Double price = p.getPrice() * p.getQuantity();

						totalprice = +price;

					}

					order.setTotal(totalprice);

					order.setProductDtoList(listoforderedproducts);

					cart.setProducts(new ArrayList<>());

					cartRepo.save(cart);

					return orderRepo.save(order);

				} else {

					throw new OrderException("Cart is Empty, Please Add Products To Place a Order !");
				}

			} else {
				throw new CartException("No Cart Found With This Customer Id : " + customer.getCustomerId());
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public List<Order> viewOrder(String key) throws LoginException, CustomerException, OrderException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			List<Order> listOfOrders = customer.getListOfOrders();

			if (!listOfOrders.isEmpty()) {
				return listOfOrders;
			} else {
				throw new OrderException("No Orders Found For You !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

}

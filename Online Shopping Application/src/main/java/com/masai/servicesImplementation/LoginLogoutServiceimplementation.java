/**
 * 
 */
package com.masai.servicesImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CurrentUserSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.LoginLogoutService;

import net.bytebuddy.utility.RandomString;

/**
 * @author tejas
 *
 */

@Service
public class LoginLogoutServiceimplementation implements LoginLogoutService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;

	@Autowired
	private CurrentUserSessionServiceImplementation currentUserSessionServiceImplementation;

	@Override
	public CurrentUserSession loginUser(User user) throws LoginException {

		Optional<Customer> optional_customer = customerRepo.findByMobileNumber(user.getId());

		if (optional_customer.isPresent()) {

			Customer customer = optional_customer.get();

			Optional<CurrentUserSession> optional_CurrentUserSession = currentUserSessionRepo
					.findByCustomerId(customer.getCustomerId());

			if (optional_CurrentUserSession.isPresent()) {

				throw new LoginException("User Already Logged In With This Customer Id : " + customer.getCustomerId());
			} else {

				if (user.getId().equals(customer.getMobileNumber())
						&& user.getPassword().equals(customer.getPassword())) {

					CurrentUserSession currentUserSession = new CurrentUserSession();

					String key = RandomString.make(6);

					currentUserSession.setCustomerId(customer.getCustomerId());
					currentUserSession.setKey(key);
					currentUserSession.setLocalDateTime(LocalDateTime.now());

					return currentUserSessionRepo.save(currentUserSession);

				} else {
					throw new LoginException("Invalid User_Id or Password");
				}
			}

		} else {
			throw new LoginException("No Registered Customer Found With This User_Id : " + user.getId());
		}

	}

	@Override
	public String logoutUser(String key) throws LogoutException, CurrentUserSessionException {

		CurrentUserSession currentUserSession = currentUserSessionServiceImplementation.getCurrentUserSession(key);

		if (currentUserSession != null) {

			currentUserSessionRepo.delete(currentUserSession);

			return "Logged Out Successfully !";
		} else {
			throw new LogoutException("No User Logged !");
		}

	}

	@Override
	public User validateUser(User user, String key) throws UserException, LoginException {

		Optional<CurrentUserSession> optional_currentUserSession = currentUserSessionRepo.findByKey(key);

		if (optional_currentUserSession.isPresent()) {

			CurrentUserSession currentUserSession = optional_currentUserSession.get();

			Optional<Customer> customer = customerRepo.findById(currentUserSession.getCustomerId());

			if (optional_currentUserSession.isPresent()) {

				Customer current_customer = customer.get();

				if (current_customer.getMobileNumber().equals(user.getId())
						&& current_customer.getPassword().equals(user.getPassword())) {
					return user;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new UserException("Invalid Key, Please Login In !");
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public Customer validateCustomer(String key) throws CustomerException, LoginException {

		Optional<CurrentUserSession> optional_currentUserSession = currentUserSessionRepo.findByKey(key);

		if (optional_currentUserSession.isPresent()) {

			CurrentUserSession currentUserSession = optional_currentUserSession.get();

			Optional<Customer> optional_customer = customerRepo.findById(currentUserSession.getCustomerId());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				return customer;

			} else {
				throw new CustomerException("Invalid Key, Please Login In !");
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

}

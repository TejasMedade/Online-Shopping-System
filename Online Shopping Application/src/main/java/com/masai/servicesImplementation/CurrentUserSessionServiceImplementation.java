/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CurrentUserSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.CurrentUserSessionService;

/**
 * @author tejas
 *
 */

@Service
public class CurrentUserSessionServiceImplementation implements CurrentUserSessionService {

	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CurrentUserSession getCurrentUserSession(String key) throws CurrentUserSessionException {

		Optional<CurrentUserSession> optional_CurrentUserSession = currentUserSessionRepo.findByKey(key);

		if (optional_CurrentUserSession.isPresent()) {

			CurrentUserSession currentUserSession = optional_CurrentUserSession.get();

			return currentUserSession;
		} else {
			throw new CurrentUserSessionException("Invalid key, Please Login In !");
		}

	}

	@Override
	public Customer getCustomerDetails(String key) throws CurrentUserSessionException, CustomerException {

		Optional<CurrentUserSession> optional_CurrentUserSession = currentUserSessionRepo.findByKey(key);

		if (optional_CurrentUserSession.isPresent()) {

			CurrentUserSession currentUserSession = optional_CurrentUserSession.get();

			Integer current_CustomerId = currentUserSession.getCustomerId();

			Optional<Customer> current_Customer = customerRepo.findById(current_CustomerId);

			if (current_Customer.isPresent()) {

				Customer customer = current_Customer.get();

				return customer;

			} else {
				throw new CustomerException("No Customer Found !");
			}

		} else {
			throw new CurrentUserSessionException("Invalid key, Please Login In !");
		}

	}

	@Override
	public Integer getCurrentUserCustomerId(String key) throws CurrentUserSessionException {

		Optional<CurrentUserSession> optional_CurrentUserSession = currentUserSessionRepo.findByKey(key);

		if (optional_CurrentUserSession.isPresent()) {

			CurrentUserSession currentUserSession = optional_CurrentUserSession.get();

			Integer current_CustomerId = currentUserSession.getCustomerId();

			return current_CustomerId;
		} else {
		}

		throw new CurrentUserSessionException("Invalid key, Please Login In !");

	}

}

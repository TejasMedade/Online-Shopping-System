/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AddressException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Address;
import com.masai.model.Customer;
import com.masai.repository.AddressRepo;
import com.masai.repository.CustomerRepo;
import com.masai.services.AddressService;

/**
 * @author tejas
 *
 */
@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private LoginLogoutServiceimplementation loginLogoutServiceImplementation;

	@Override
	public String addAddress(String key, Address address) throws CustomerException, LoginException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			customer.setAddress(address);

			addressRepo.save(address);

			return "Address Added Successfully !";

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public String updateAddress(String key, Address address) throws CustomerException, LoginException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			customer.setAddress(address);

			addressRepo.save(address);

			return "Address Updated Successfully !";

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public String removeAddress(String key, Integer addressId) throws CustomerException, LoginException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Address> optional_address = addressRepo.findById(addressId);

			addressRepo.delete(optional_address.get());

			return "Address Deleted Successfully !";

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public List<Address> viewAllAddress(String key) throws CustomerException, LoginException, AddressException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			List<Address> listofaddresses = addressRepo.findAll();

			if (!listofaddresses.isEmpty()) {

				return listofaddresses;
			} else {
				throw new AddressException("No Addresses Found in The Database !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public Address viewAddress(String key) throws CustomerException, LoginException, AddressException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Address address = customer.getAddress();

			if (address != null) {

				return address;

			} else {
				throw new AddressException("No Addresses Has Been Added By The Customer !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

}

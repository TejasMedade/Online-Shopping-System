/**
 * 
 */
package com.masai.services;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CurrentUserSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.User;

/**
 * @author tejas
 *
 */

public interface LoginLogoutService {

	public CurrentUserSession loginUser(User user) throws LoginException;

	public String logoutUser(String key) throws LogoutException, CurrentUserSessionException;

	public User validateUser(User user, String key) throws UserException, LoginException;

	public Customer validateCustomer(String key) throws LoginException, CustomerException;
}

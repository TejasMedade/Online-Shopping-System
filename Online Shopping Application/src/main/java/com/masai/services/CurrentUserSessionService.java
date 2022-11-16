/**
 * 
 */
package com.masai.services;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CurrentUserSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

/**
 * @author tejas
 *
 */

public interface CurrentUserSessionService {

	public CurrentUserSession getCurrentUserSession(String key) throws CurrentUserSessionException;

	public Customer getCustomerDetails(String key) throws CurrentUserSessionException, CustomerException;

	public Integer getCurrentUserCustomerId(String key) throws CurrentUserSessionException;

}

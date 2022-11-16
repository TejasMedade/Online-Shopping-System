/**
 * 
 */
package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CurrentUserSessionException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.services.LoginLogoutService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/user")
public class LoginLogoutController {

	@Autowired
	private LoginLogoutService loginLogoutService;

	@GetMapping("/login")
	public ResponseEntity<CurrentUserSession> loginUserHandler(@RequestBody User user) throws LoginException {

		CurrentUserSession currentUserSession = loginLogoutService.loginUser(user);

		return new ResponseEntity(currentUserSession, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutUserHandler(@RequestParam String key)
			throws LogoutException, CurrentUserSessionException {

		String result = loginLogoutService.logoutUser(key);

		return new ResponseEntity(result, HttpStatus.OK);

	}

	@GetMapping("/validateUser")
	public ResponseEntity<User> validateUserHandler(@RequestBody User user, @RequestParam String key)
			throws UserException, LoginException {

		User validated_user = loginLogoutService.validateUser(user, key);

		return new ResponseEntity(validated_user, HttpStatus.OK);
	}

	@GetMapping("/validateCustomer")
	public ResponseEntity<Customer> validateCustomerHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Customer validated_customer = loginLogoutService.validateCustomer(key);

		return new ResponseEntity(validated_customer, HttpStatus.OK);
	}

}

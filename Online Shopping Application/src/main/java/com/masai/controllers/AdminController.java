/**
 * 
 */
package com.masai.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;
import com.masai.services.AdminService;

/**
 * @author tejas
 *
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public ResponseEntity<Admin> addAdminHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestBody Admin admin)
			throws AdminException, UserException, LoginException {

		Admin savedadmin = adminService.addAdmin(userId, userPassword, key, admin);

		return new ResponseEntity<>(savedadmin, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAdminHandler(@RequestParam String key, @Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam Integer adminId)
			throws AdminException, UserException, LoginException {

		String result = adminService.deleteAdmin(adminId, key, userId, userPassword);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/view")
	public ResponseEntity<Admin> viewAdminByIdHandler(@RequestParam String key, @Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam Integer adminId)
			throws AdminException, UserException, LoginException {

		Admin savedadmin = adminService.viewAdminById(key, userId, userPassword, adminId);

		return new ResponseEntity<>(savedadmin, HttpStatus.ACCEPTED);
	}

}

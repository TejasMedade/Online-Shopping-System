/**
 * 
 */
package com.masai.services;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;

/**
 * @author tejas
 *
 */
public interface AdminService {

	public Admin addAdmin(String userId,String userPassword, String key, Admin admin) throws AdminException, UserException, LoginException;
	
	public Admin viewAdminById(String key,String userId, String userPassword, Integer adminId) throws AdminException, UserException, LoginException;
	
	public String deleteAdmin(Integer adminId, String userId, String userPassword, String key) throws AdminException, UserException, LoginException;
}

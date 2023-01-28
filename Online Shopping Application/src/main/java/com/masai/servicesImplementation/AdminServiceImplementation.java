/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;
import com.masai.repository.AdminRepo;
import com.masai.services.AdminService;
import com.masai.services.LoginLogoutAdminService;

/**
 * @author tejas
 *
 */

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private LoginLogoutAdminService loginLogoutAdminService;

	@Override
	public Admin addAdmin(String userId,String userPassword, String key, Admin admin) throws AdminException, UserException, LoginException {

		Admin validated_admin = loginLogoutAdminService.authenticateAdmin(userId,userPassword, key);

		if (validated_admin != null) {

			Optional<Admin> optionaladmin = adminRepo.findById(admin.getAdminId());

			if (optionaladmin.isEmpty()) {

				Admin savedadmin = adminRepo.save(admin);

				return savedadmin;

			} else {
				throw new AdminException("Admin Already Exists with this Admin Id : " + admin.getAdminId());
			}

		} else {
			throw new AdminException("Invalid Credentials ! Incorrect Admin Id or Password.");
		}

	}

	@Override
	public String deleteAdmin(Integer adminId, String userId, String userPassword, String key) throws AdminException, UserException, LoginException {

		Admin validated_admin = loginLogoutAdminService.authenticateAdmin(userId, userPassword, key);

		if (validated_admin != null) {

			Optional<Admin> optionaladmin = adminRepo.findById(adminId);
			
			if(optionaladmin.isPresent()) {
				
				 adminRepo.deleteById(adminId);
				 
				 return "Admin Deleted Successfully !" ;
				
			}else {
				throw new AdminException("No Registered Admin Exists with this Admin Id : " + adminId);
			}
		} else {
			throw new AdminException("Invalid Credentials ! Incorrect Admin Id or Password.");
		}
	}

	@Override
	public Admin viewAdminById(String key,String userId, String userPassword, Integer adminId) throws AdminException, UserException, LoginException {
		
		Admin validated_admin = loginLogoutAdminService.authenticateAdmin(userId, userPassword, key);

		if (validated_admin != null) {

			Optional<Admin> optionaladmin = adminRepo.findById(adminId);
			
			if(optionaladmin.isPresent()) {
			
				 
				 return optionaladmin.get();
				
			}else {
				throw new AdminException("No Registered Admin Exists with this Admin Id : " + adminId);
			}
		} else {
			throw new AdminException("Invalid Credentials ! Incorrect Admin Id or Password.");
		}
		
	}

}

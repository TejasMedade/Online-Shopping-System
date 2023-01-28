/**
 * 
 */
package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.Product;

/**
 * @author tejas
 *
 */

public interface ProductService {

	public List<Product> viewallProducts() throws ProductException;

	// Admin Role
	public Product insertProduct(String userid,String userpassword , String key, Product product)
			throws LoginException, AdminException, UserException;

	// Admin Role
	public Product updateProduct(String userId,String userPassword, String key, Product product)
			throws AdminException, LoginException, ProductException, UserException;

	public Product viewProduct(Integer productId) throws ProductException;

	public List<Product> viewProductByCategory(String category_Name) throws ProductException;

	// Admin Role
	public String removeProduct(String userId,String userPassword, String key, Integer product_Id)
			throws AdminException, LoginException, ProductException, UserException;

}

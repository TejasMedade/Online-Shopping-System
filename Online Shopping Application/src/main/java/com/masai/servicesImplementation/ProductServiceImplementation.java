/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.ProductRepo;
import com.masai.services.ProductService;

/**
 * @author tejas
 *
 */
@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;

	@Autowired
	private LoginLogoutServiceimplementation loginLogoutServiceimplementation;

	@Override
	public List<Product> viewallProducts() throws ProductException {

		List<Product> listofproducts = productRepo.findAll();

		if (listofproducts.isEmpty()) {
			throw new ProductException("No Products Found in the Database !");
		} else {
			return listofproducts;
		}

	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {

		Optional<Product> optional_product = productRepo.findById(productId);

		if (optional_product.isPresent()) {

			Product product = optional_product.get();

			return product;

		} else {
			throw new ProductException("No Product Found by this Product Id : " + productId);
		}

	}

	@Override
	public List<Product> viewProductByCategory(String category_Name) throws ProductException {

		List<Product> listofproducts = productRepo.viewByCategoryName(category_Name);

		if (listofproducts.isEmpty()) {
			throw new ProductException("No Products Found Under This Category : " + category_Name);
		} else {

			return listofproducts;

		}
	}

	@Override
	public Product insertProduct(String key, Product product) throws CustomerException, LoginException {

		Customer customer = loginLogoutServiceimplementation.validateCustomer(key);

		if (customer != null) {

			Product added_product = productRepo.save(product);

			return added_product;
		} else {
			throw new CustomerException("Invalid Admin Key, Please Login In !");
		}

	}

	@Override
	public Product updateProduct(String key, Product product)
			throws CustomerException, LoginException, ProductException {

		Customer customer = loginLogoutServiceimplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Product> optional_product = productRepo.findById(product.getProductId());

			if (optional_product.isPresent()) {

				return productRepo.save(product);

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}
		} else {
			throw new CustomerException("Invalid Admin Key, Please Login In !");
		}

	}

	@Override
	public String removeProduct(String key, Integer product_Id)
			throws CustomerException, LoginException, ProductException {

		Customer customer = loginLogoutServiceimplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Product> optional_product = productRepo.findById(product_Id);

			if (optional_product.isPresent()) {

				Product product = optional_product.get();

				productRepo.deleteById(product_Id);

				return " Product Deleted Successfully !";

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}
		} else {
			throw new CustomerException("Invalid Admin Key, Please Login In !");
		}

	}

}

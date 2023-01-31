/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.Product;
import com.masai.services.ProductService;

/**
 * @author tejas
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/viewProducts")
	public ResponseEntity<List<Product>> viewAllProductsHandler() throws ProductException {

		List<Product> listofproducts = productService.viewallProducts();

		return new ResponseEntity<List<Product>>(listofproducts, HttpStatus.OK);
	}

	// Admin Role
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProductHandler(@RequestParam String key, @Valid @RequestBody Product product,
			@Valid @RequestParam String userid, @Valid @RequestParam String userpassword)
			throws CustomerException, LoginException, AdminException, UserException {

		Product added_product = productService.insertProduct(userid, userpassword, key, product);

		return new ResponseEntity<Product>(added_product, HttpStatus.ACCEPTED);
	}

	// Admin Role
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProductHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestBody Product product)
			throws LoginException, ProductException, AdminException, UserException {

		Product updated_product = productService.updateProduct(userId, userPassword, key, product);

		return new ResponseEntity<Product>(updated_product, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewProduct")
	public ResponseEntity<Product> viewProductHandler(@Valid @RequestParam Integer productId) throws ProductException {

		Product product = productService.viewProduct(productId);

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/viewProductByCategory")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@Valid @RequestParam String category_Name)
			throws ProductException {

		List<Product> listofproducts = productService.viewProductByCategory(category_Name);

		return new ResponseEntity<List<Product>>(listofproducts, HttpStatus.OK);

	}

	// Admin Role
	@PostMapping("/deleteProduct")
	public ResponseEntity<String> removeProductHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestParam Integer product_Id)
			throws CustomerException, LoginException, ProductException, AdminException, UserException {

		String result = productService.removeProduct(userId, userPassword, key, product_Id);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

}

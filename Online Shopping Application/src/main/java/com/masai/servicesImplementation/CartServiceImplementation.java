/**
 * 
 */
package com.masai.servicesImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ProductDTO;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.CartRepo;
import com.masai.repository.ProductRepo;
import com.masai.services.CartService;
import com.masai.services.LoginLogoutService;

/**
 * @author tejas
 *
 */

@Service
public class CartServiceImplementation implements CartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private LoginLogoutService loginLogoutServiceImplementation;

	@Override
	public String deleteallproducts(String key)
			throws ProductException, CartException, LoginException, CustomerException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				List<ProductDTO> listofproducts = cart.getProducts();

				if (listofproducts.isEmpty()) {

					throw new ProductException("Cart is Empty ! No Products found in the cart !");
				} else {

					List<ProductDTO> remove_products = cart.getProducts();

					for (ProductDTO p : remove_products) {

						Optional<Product> optional_product = productRepo.findById(p.getProductId());

						Product product = optional_product.get();

						Integer available_quantity = product.getQuantity();

						product.setQuantity(available_quantity + p.getQuantity());

						productRepo.save(product);

					}

					listofproducts.removeAll(remove_products);

					cart.setProducts(listofproducts);

					cartRepo.save(cart);

					return "All Products are Deleted From The Cart !";
				}

			} else {
				throw new CartException("No Customer Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public List<ProductDTO> viewallproducts(String key)
			throws LoginException, CustomerException, ProductException, CartException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				List<ProductDTO> listofproducts = cart.getProducts();

				if (listofproducts.isEmpty()) {

					throw new ProductException("Cart is Empty ! No Products found in the cart !");
				} else {

					return listofproducts;

				}

			} else {
				throw new CartException("No Customer Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public Cart addproduct(Integer productId, Integer quantity, String key)
			throws ProductException, LoginException, CustomerException, CartException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Product> optional_product = productRepo.findById(productId);

			if (optional_product.isPresent()) {

				Product database_product = optional_product.get();

				Integer available_quantity = database_product.getQuantity();

				if (available_quantity >= quantity) {

					ProductDTO productDto = new ProductDTO();

					productDto.setProductId(database_product.getProductId());
					productDto.setProductName(database_product.getProductName());
					productDto.setColour(database_product.getColour());
					productDto.setDimension(database_product.getDimension());
					productDto.setManufacturer(database_product.getDimension());
					productDto.setPrice(database_product.getPrice());

					productDto.setQuantity(quantity);

					Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

					if (optional_cart.isPresent()) {

						Cart cart = optional_cart.get();

						List<ProductDTO> listofproducts = cart.getProducts();

						listofproducts.add(productDto);

						cart.setProducts(listofproducts);

						cartRepo.save(cart);

						database_product.setQuantity(available_quantity - quantity);

						productRepo.save(database_product);

						return cart;

					} else {
						throw new CartException("No Customer Cart Found, Please Login In !");
					}

				} else {
					throw new ProductException("Oops ! Available Product Quantity is : " + available_quantity);
				}

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}

		} else {

			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public Cart deleteproduct(Integer productId, String key)
			throws LoginException, CustomerException, CartException, ProductException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				Optional<Product> optional_product = productRepo.findById(productId);

				if (optional_product.isPresent()) {

					Product database_product = optional_product.get();

					List<ProductDTO> list_of_products = cart.getProducts();

					if (!list_of_products.isEmpty()) {

						Boolean flag = false;

						for (int i = 0; i < list_of_products.size(); i++) {

							ProductDTO p = list_of_products.get(i);

							if (Objects.equals(p.getProductId(), productId)) {

								ProductDTO delete_product = p;

								System.out.println(delete_product);

								Integer deleted_quantity = delete_product.getQuantity();

								database_product.setQuantity(database_product.getQuantity() + deleted_quantity);

								list_of_products.remove(delete_product);

								System.out.println("List" + list_of_products);

								cart.setProducts(list_of_products);

								flag = true;

							}
						}

						if (Boolean.TRUE.equals(flag)) {

							cartRepo.save(cart);

							productRepo.save(database_product);

							return cart;

						} else {
							throw new ProductException("No Products Found in the Cart !");
						}
					}

					else {
						throw new ProductException("No Products Found in the Cart !");
					}

				} else {
					throw new ProductException("No Products Found With The ProductId : " + productId);
				}

			} else {
				throw new CartException("No Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public Cart udpateproductquantity(String key, Integer productId, Integer quantity)
			throws CartException, LoginException, CustomerException, ProductException {

		Customer customer = loginLogoutServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Product> optional_product = productRepo.findById(productId);

			if (optional_product.isPresent()) {

				Product product = optional_product.get();

				Integer available_quantity = product.getQuantity();

				Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

				if (optional_cart.isPresent()) {

					Cart cart = optional_cart.get();

					List<ProductDTO> listofproducts = cart.getProducts();

					if (!listofproducts.isEmpty()) {

						Boolean flag = false;

						for (ProductDTO s : listofproducts) {

							if (Objects.equals(s.getProductId(), productId)) {

								Integer intial_cart_quantity = s.getQuantity();

								Integer total_quantity = intial_cart_quantity + available_quantity;

								if (total_quantity >= quantity) {

									s.setQuantity(quantity);

									product.setQuantity(total_quantity - quantity);

									flag = true;
								} else {
									throw new ProductException(
											"OOps ! Available Product Quantity is : " + available_quantity);
								}

							}

						}

						if (flag == true) {

							cartRepo.save(cart);

							productRepo.save(product);

							return cart;
						} else {
							throw new ProductException(
									"No Products Found In the Cart By This Product Id : " + productId);
						}

					} else {
						throw new ProductException("No Products Found In The Cart !");
					}

				} else {
					throw new CartException("No Cart Found with this Customer Id : " + customer.getCustomerId());
				}

			} else {
				throw new ProductException("No Products Found By This Product Id :" + productId);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

}

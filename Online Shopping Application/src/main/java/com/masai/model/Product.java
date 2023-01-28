/**
 * 
 */
package com.masai.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;

	@NotBlank(message = "{Product.name.invalid}")
	@NotEmpty(message = "{Product.name.invalid}")
	@NotNull(message = "{Product.name.invalid}")
	private String productName;

	@NotNull(message = "{Product.price.invalid}")
	private Double price;

	private String colour;

	private String dimension;

	@NotBlank(message = "{Product.specification.invalid}")
	@NotEmpty(message = "{Product.specification.invalid}")
	@NotNull(message = "{Product.specification.invalid}")
	private String specification;

	@NotBlank(message = "{Product.manufacturer.invalid}")
	@NotEmpty(message = "{Product.manufacturer.invalid}")
	@NotNull(message = "{Product.manufacturer.invalid}")
	private String manufacturer;

	@Embedded
	private Category category;

	@NotNull(message = "{Product.quantity.invalid}")
	private Integer quantity;

}

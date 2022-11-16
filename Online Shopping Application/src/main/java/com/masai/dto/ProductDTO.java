/**
 * 
 */
package com.masai.dto;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.masai.model.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Data
@NoArgsConstructor
@Entity
public class ProductDTO {

	@Id
	private Integer productId;

	private String productName;

	private Double price;

	private String colour;

	private String dimension;

	private String manufacturer;

	private Integer quantity;

}

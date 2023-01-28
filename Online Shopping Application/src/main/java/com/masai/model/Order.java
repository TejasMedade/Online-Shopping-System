/**
 * 
 */
package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.masai.dto.ProductDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@NotNull(message = "{Order.date.invalid}")
	private LocalDate orderDate;

	@NotBlank(message = "{Order.status.invalid}")
	@NotEmpty(message = "{Order.status.invalid}")
	@NotNull(message = "{Order.status.invalid}")
	private String orderStatus;

	@NotBlank(message = "{Order.location.invalid}")
	@NotEmpty(message = "{Order.location.invalid}")
	@NotNull(message = "{Order.location.invalid}")
	private String location;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@ElementCollection
	private List<ProductDTO> productDtoList = new ArrayList<>();

	@NotNull(message = "{Order.total.invalid}")
	private Double total;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

}

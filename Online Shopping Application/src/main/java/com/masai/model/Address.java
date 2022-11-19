/**
 * 
 */
package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
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
public class Address {

	@Id
	@NotNull
	@NotBlank
	@NotEmpty
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;

	@NotNull(message = "{Address.streetname.invalid}")
	@NotBlank(message = "{Address.streetname.invalid}")
	@NotEmpty(message = "{Address.streetname.invalid}")
	private String streetName;

	@NotNull(message = "{Address.buildingname.invalid}")
	@NotBlank(message = "{Address.buildingname.invalid}")
	@NotEmpty(message = "{Address.buildingname.invalid}")
	private String buildingName;

	@NotNull(message = "{Address.landmark.invalid}")
	@NotBlank(message = "{Address.landmark.invalid}")
	@NotEmpty(message = "{Address.landmark.invalid}")
	private String landmark;

	@NotNull(message = "{Address.city.invalid}")
	@NotBlank(message = "{Address.city.invalid}")
	@NotEmpty(message = "{Address.city.invalid}")
	@Min(value = 3, message = "{Address.city.invalid}")
	private String city;

	@NotNull(message = "{Address.state.invalid}")
	@NotBlank(message = "{Address.state.invalid}")
	@NotEmpty(message = "{Address.state.invalid}")
	@Min(value = 3, message = "{Address.state.invalid}")
	private String state;

	@NotNull(message = "{Address.country.invalid}")
	@NotBlank(message = "{Address.country.invalid}")
	@NotEmpty(message = "{Address.country.invalid}")
	@Min(value = 3, message = "{Address.country.invalid}")
	private String country;

	@NotNull(message = "{Address.pincode.invalid}")
	@NotBlank(message = "{Address.pincode.invalid}")
	@NotEmpty(message = "{Address.pincode.invalid}")
	@Min(value = 6, message = "{Address.pincode.invalid}")
	private String pincode;

}

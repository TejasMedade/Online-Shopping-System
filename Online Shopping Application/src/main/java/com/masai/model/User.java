/**
 * 
 */
package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Entity
@Data
@NoArgsConstructor
public class User {

	// Id should be Contact Number
	@Id
	@NotBlank(message="User.contact.null")
	@NotEmpty(message="User.contact.null")
	@NotNull(message="User.contact.null")
	@Min(value = 10 , message="User.contact.invalid")
	private String id;
	
	@NotBlank(message="User.password.invalid")
	@NotEmpty(message="User.password.invalid")
	@NotNull(message="User.password.invalid")
	@Min(value = 6, message = "{User.password.invalid}")
	@Max(value = 8, message = "{User.password.invalid}")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,8}$", message = "{Admin.password.invalid}")
	private String password;

	// Choose From Admin or Customer
	@NotBlank(message="User.role.invalid")
	@NotEmpty(message="User.role.invalid")
	@NotNull(message="User.role.invalid")
	private String role;
}

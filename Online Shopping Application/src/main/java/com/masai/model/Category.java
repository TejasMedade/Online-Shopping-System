/**
 * 
 */
package com.masai.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Data
@NoArgsConstructor
@Embeddable
public class Category {
	
	@NotNull(message="{Category.id.invalid}")
	@NotBlank(message="{Category.id.invalid}")
	@NotEmpty(message="{Category.id.invalid}")
	@Size(min = 4, max = 5, message = "{Category.id.invalid}")
	private Integer catId;
	
	@NotNull(message = "{Category.name.invalid}")
	@NotBlank(message = "{Category.name.invalid}")
	@NotEmpty(message = "{Category.name.invalid}")
	private String categoryName;

}

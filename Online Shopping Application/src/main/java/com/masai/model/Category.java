/**
 * 
 */
package com.masai.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
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

@Data
@NoArgsConstructor
@Embeddable
public class Category {
	
	@NotNull(message="{Category.id.invalid}")
	@NotBlank(message="{Category.id.invalid}")
	@NotEmpty(message="{Category.id.invalid}")
	@Min(value = 4, message = "{Category.id.invalid}")
	@Max(value = 4, message = "{Category.id.invalid}")
	private Integer catId;
	
	@NotNull(message="{Category.name.invalid}")
	@NotBlank(message="{Category.name.invalid}")
	@NotEmpty(message="{Category.name.invalid}")
	private String categoryName;

}

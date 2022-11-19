/**
 * 
 */
package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentCustomerSession {

	@Id
	@NotNull
	@NotBlank
	@NotEmpty
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currentSessionId;

	@NotNull
	@NotBlank
	@NotEmpty
	private Integer customerId;

	@NotNull
	@NotBlank
	@NotEmpty
	private String key;

	@NotNull
	@NotBlank
	@NotEmpty
	private LocalDateTime localDateTime;

}

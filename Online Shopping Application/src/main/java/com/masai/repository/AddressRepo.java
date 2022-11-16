/**
 * 
 */
package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Address;

/**
 * @author tejas
 *
 */
public interface AddressRepo extends JpaRepository<Address, Integer> {

}

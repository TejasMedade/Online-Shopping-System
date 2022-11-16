/**
 * 
 */
package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentUserSession;

/**
 * @author tejas
 *
 */
@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public Optional<CurrentUserSession> findByKey(String key);

	public Optional<CurrentUserSession> findByCustomerId(Integer customerId);
}

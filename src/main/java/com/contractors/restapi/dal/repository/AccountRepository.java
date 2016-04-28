/**
 * 
 */
package com.contractors.restapi.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contractors.restapi.dal.entity.Account;
import java.util.Optional;
/**
 * @author sergio
 * JPA Repository interface to insert, update, delete, ...
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username);
}

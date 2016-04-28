/**
 * 
 */
package com.contractors.restapi.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contractors.restapi.dal.entity.Car;

import java.util.Collection;
/**
 * @author sergio
 * JPA Repository interface to insert, update, delete, ... Cars
 */
public interface CarRepository extends JpaRepository<Car, Long> {
	Collection<Car> findByAccountUsername(String username);
}

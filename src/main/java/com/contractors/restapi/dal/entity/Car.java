package com.contractors.restapi.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author sergio
 * Car entity, owned by a user with an account
 */
@Entity
public class Car {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@JsonIgnore
    @ManyToOne
    private Account account;
	
	public String brand;
    public String model;

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
	
	Car() { // jpa only
    }
	
	/**
	 * Instantiates a new car with associated user account, brand and model.
	 * @param account: Account of the car owner
	 * @param brand: Brand of the car
	 * @param model: Car model
	 */
	public Car(Account account, String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.account = account;
    }
}

package com.contractors.restapi.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
    @GeneratedValue
    private Long id;
	
	public Long getId() {
        return id;
    }
	
	public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    @JsonIgnore
    public String password;
    public String username;

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    Account() { // jpa only
    }
}

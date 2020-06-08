package com.miguelMercado.project2.repository;
import com.miguelMercado.project2.model.Customer;
import com.miguelMercado.project2.model.Region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByNumberID(String numberID);
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByRegion(Region region);
	
}

package com.miguelMercado.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelMercado.project2.model.Customer;
import com.miguelMercado.project2.model.Region;
import com.miguelMercado.project2.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerSericce{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findCustomerAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomerByRegion(Region region) {	
		return customerRepository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
		if(customerDB != null) {
			return customerDB;
		}
		
		
		customer.setState("CREATED");
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Customer customerDB = this.getCustomer(customer.getId());
		if(customerDB == null) {
			return null;
		}
		
		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setEmail(customer.getEmail());
		customerDB.setPhotoUrl(customer.getPhotoUrl());
		
		return customerRepository.save(customerDB);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		Customer customerDB = this.getCustomer(customer.getId());
		if(customerDB == null) {
			return null;
		}
		customerDB.setState("DELETED");
		return customerRepository.save(customerDB); 
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	

}

package com.miguelMercado.project2.service;

import java.util.List;

import com.miguelMercado.project2.model.Customer;
import com.miguelMercado.project2.model.Region;

public interface CustomerSericce {

	public List<Customer> findCustomerAll();
	public List<Customer> findCustomerByRegion(Region region);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	public Customer getCustomer(Long id);
}

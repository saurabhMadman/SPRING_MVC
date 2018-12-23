package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.Customer;

public interface CustomerService{
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomers(int theid);

	public void delete(int theid);

}

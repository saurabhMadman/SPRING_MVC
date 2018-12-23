package com.springdemo.dao;

import java.util.List;
import com.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomer();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int theid);

	public void deleteCustomer(int theid);

}

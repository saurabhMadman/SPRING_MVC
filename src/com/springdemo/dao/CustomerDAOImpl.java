package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomer() {
		
		//session from hibernate
		Session currentSession =sessionFactory.getCurrentSession();
		//Query
		Query<Customer> query =currentSession.createQuery("from Customer order by firstName",Customer.class);
		//ResultSet
		List<Customer> customers = query.getResultList();
			
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentsession =sessionFactory.getCurrentSession();
		currentsession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int theid) {
		Session currentSession =sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class,theid);
		return customer;
	}

	@Override
	public void deleteCustomer(int theid) {
		Session currentSession =sessionFactory.getCurrentSession();
		//Query
		Query query =currentSession.createQuery("delete from Customer where id=:customerid");
		query.setParameter("customerid", theid);
		query.executeUpdate();
		
	}

	    @Override
     public List<Customer> searchCustomers(String theSearchName) {

	        // get the current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();
	        
	        Query<Customer> theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {

	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer order by first_name", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	        
	    }

}

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
		Query<Customer> query =currentSession.createQuery("from Customer order by first_name",Customer.class);
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

}

package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@ControllerAdvice
@RequestMapping("/customer")
public class CustomerController {

	//@Autowired
	//private CustomerDAO customerdao;  // Autowiring will search for implemation class that implement this interface.
	
	@Autowired
	private CustomerService customerService;  //throught Service Layer
	
	@GetMapping("/list")
	public String listCustomers(Model themodel)
	{
		//get data from Service Layer
		List<Customer> customer=customerService.getCustomers();
		
		//passing data to model
		themodel.addAttribute("customer",customer);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String ShowFormForAdd(Model themodel)
	{
		Customer thecustomer =new Customer();
		
		themodel.addAttribute("customer",thecustomer);
		return "customer-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer)
	{
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theid, Model model)
	{
		Customer customer =customerService.getCustomers(theid);
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String CustomerDelete(@RequestParam("customerId") int theid, Model model)
	{
		customerService.delete(theid);
		return "redirect:/customer/list";
		
	}
	
	  @PostMapping("/search")
	    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
	                                    Model theModel) {

	        // search customers from the service
	        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
	                
	        // add the customers to the model
	        theModel.addAttribute("customers", theCustomers);

	        return "list-customers";        
	    }
	
}

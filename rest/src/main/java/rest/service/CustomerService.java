package rest.service;

import java.util.List;

import rest.model.Customer;

/**
 * Interface for Customer operations 
 *
 */
public interface CustomerService {

	/**
	 * lists the customer by id. This is not directly invoked by rest end point. 
	 * @param id
	 * @return
	 */
	Customer listById(long id);

	/**
	 * create the new customer
	 * @param customer
	 * @return
	 */
	Customer addCustomer(Customer customer);

	/**
	 * removes the customer by given id
	 * @param id
	 */
	void removeCustomerById(long id);

	/**
	 * list all the customers which have been added 
	 * @return
	 */
	List<Customer> listAllCustomers();


}

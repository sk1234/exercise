package rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rest.model.Customer;
import rest.service.CustomerService;

/**
 * Rest controller for Customer
 *
 */
@RestController
public class CustomerRestController {

	@Autowired
	CustomerService customerService;

	/**
	 * adds a customer using POST request method
	 * 
	 * @param customer
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		System.out.println("CustomerRestController-addCustomer(), Adding Customer " + customer.getFirstName() + " "
				+ customer.getSurName());

		customerService.addCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * removes a customer with a specific id using DELETE request method
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> removeCustomer(@PathVariable("id") long id) {
		System.out.println("CustomerRestController-removeCustomer(), Getting & Removing Customer with id " + id);

		Customer customer = customerService.listById(id);
		if (customer == null) {
			System.out.println("CustomerRestController-removeCustomer(), Unable to remove Customer with id " + id
					+ ", id not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		customerService.removeCustomerById(id);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	/**
	 * get all customers using GET request method
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		System.out.println("CustomerRestController-listAllCustomers(), Getting all Customers ");
		List<Customer> customers = customerService.listAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

}

package rest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import rest.model.Customer;

/**
 * Implementation class for CustomerService interface
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Customer> customers = new ArrayList<>();

	/* (non-Javadoc)
	 * @see rest.service.CustomerService#listAllCustomers()
	 */
	public List<Customer> listAllCustomers() {
		return customers;
	}

	/* (non-Javadoc)
	 * @see rest.service.CustomerService#listById(long)
	 */
	public Customer listById(long id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see rest.service.CustomerService#addCustomer(rest.model.Customer)
	 */
	public Customer addCustomer(Customer customer) {
		customer.setId(counter.incrementAndGet());
		customers.add(customer);
		return customer;
	}

	/* (non-Javadoc)
	 * @see rest.service.CustomerService#removeCustomerById(long)
	 */
	public void removeCustomerById(long id) {

		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = iterator.next();
			if (customer.getId() == id) {
				iterator.remove();
			}
		}
	}

}

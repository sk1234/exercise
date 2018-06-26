package rest.model;

/**
 * Model class for Customer
 *
 */
public class Customer {

	private long id;
	private String firstName;
	private String surName;

	/**
	 * Empty constructor
	 */
	public Customer() {
	}

	/**
	 * Constructor with parameters to initialise the Customer object
	 * 
	 * @param id
	 * @param firstName
	 * @param surName
	 */
	public Customer(long id, String firstName, String surName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", surName=" + surName + "]";
	}

}

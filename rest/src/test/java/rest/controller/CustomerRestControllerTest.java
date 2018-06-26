package rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import rest.model.Customer;
import rest.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void getCustomerShouldReturnListOfAllCustomers() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer1 = new Customer(1, "FirstName1", "SurName1");
		customerList.add(customer1);
		Mockito.when(customerService.listAllCustomers()).thenReturn(customerList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":1,\"firstName\":\"FirstName1\",\"surName\":\"SurName1\"}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

	@Test
	public void addCustomerShouldReturnCreateStatusAndCustomerLocation() throws Exception {

		Customer customer1 = new Customer(1, "FirstName1", "SurName1");

		String createRequestCustomerJson = "{\"id\":\"\",\"firstName\":\"FirstName1\",\"surName\":\"SurName1\"}";

		Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(customer1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer").accept(MediaType.APPLICATION_JSON)
				.content(createRequestCustomerJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertThat(response.getHeader(HttpHeaders.LOCATION), containsString("http://localhost/customer"));

	}

	@Test
	public void removeCustomerShouldReturnNoContentStatus() throws Exception {

		Customer customer1 = new Customer(1, "FirstName1", "SurName1");
		Mockito.when(customerService.listById(customer1.getId())).thenReturn(customer1);
		Mockito.doNothing().when(customerService).removeCustomerById(customer1.getId());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/{id}", customer1.getId())
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

	}

}

package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import App.App;
import App.utils.DbTestUtil;
import App.services.AddressService;

import App.models.Address;
import App.models.City;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestAddressController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AddressService addressService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupAddress() {
		addressService.addAddress(new Address("street_1", "number_1", new City("name_1", null)));
		addressService.addAddress(new Address("street_2", "number_2", new City("name_2", null)));
		addressService.addAddress(new Address("street_3", "number_3", new City("name_3", null)));
		addressService.addAddress(new Address("street_4", "number_4", new City("name_4", null)));
		addressService.addAddress(new Address("street_5", "number_5", new City("name_5", null)));
	}

	@After
	public void afterAddress() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "address", "city");
	}

	@Test
	public void getAddress() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/address").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getAddressId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.street", equalTo("street_5")))
		.andExpect(jsonPath("$.number", equalTo("number_5")))
		.andExpect(jsonPath("$.city.name", equalTo("name_5")))
		.andExpect(jsonPath("$.city.country", equalTo(null)));
	}
}
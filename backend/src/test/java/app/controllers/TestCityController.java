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
import App.services.CityService;

import App.models.Country;
import App.models.City;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCityController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CityService cityService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupCity() {
		cityService.addCity(new City("name_1", new Country("name_1", null)));
		cityService.addCity(new City("name_2", new Country("name_2", null)));
		cityService.addCity(new City("name_3", new Country("name_3", null)));
		cityService.addCity(new City("name_4", new Country("name_4", null)));
		cityService.addCity(new City("name_5", new Country("name_5", null)));
	}

	@After
	public void afterCity() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "city", "country");
	}

	@Test
	public void getCity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/city").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getCityId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/city/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.country.name", equalTo("name_5")));
	}
}
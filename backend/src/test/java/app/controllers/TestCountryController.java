package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashSet;

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
import App.services.CountryService;

import App.models.City;
import App.models.Country;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCountryController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CountryService countryService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupCountry() {
		countryService.addCountry(new Country("name_1", new HashSet<City>(Arrays.asList(new City("name_1", null)))));
		countryService.addCountry(new Country("name_2", new HashSet<City>(Arrays.asList(new City("name_2", null)))));
		countryService.addCountry(new Country("name_3", new HashSet<City>(Arrays.asList(new City("name_3", null)))));
		countryService.addCountry(new Country("name_4", new HashSet<City>(Arrays.asList(new City("name_4", null)))));
		countryService.addCountry(new Country("name_5", new HashSet<City>(Arrays.asList(new City("name_5", null)))));
	}

	@After
	public void afterCountry() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "country", "city");
	}

	@Test
	public void getCountry() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/country").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getCountryId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/country/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")));
	}
}
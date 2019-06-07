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
import App.services.ScientificFieldService;

import App.models.ScientificField;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestScientificFieldController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ScientificFieldService scientificFieldService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupScientificField() {
		scientificFieldService.addScientificField(new ScientificField("name_1"));
		scientificFieldService.addScientificField(new ScientificField("name_2"));
		scientificFieldService.addScientificField(new ScientificField("name_3"));
		scientificFieldService.addScientificField(new ScientificField("name_4"));
		scientificFieldService.addScientificField(new ScientificField("name_5"));
	}

	@After
	public void afterScientificField() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "scientific_field");
	}

	@Test
	public void getScientificField() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/scientificfield").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getScientificFieldId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/scientificfield/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")));
	}
}
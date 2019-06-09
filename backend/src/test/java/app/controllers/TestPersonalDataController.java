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
import App.services.PersonalDataService;

import App.models.PersonalData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestPersonalDataController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PersonalDataService personalDataService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupPersonalData() {
		personalDataService.addPersonalData(new PersonalData("firstName_1", "lastName_1", "personalNumber_1", "profilePicturePath_1"));
		personalDataService.addPersonalData(new PersonalData("firstName_2", "lastName_2", "personalNumber_2", "profilePicturePath_2"));
		personalDataService.addPersonalData(new PersonalData("firstName_3", "lastName_3", "personalNumber_3", "profilePicturePath_3"));
		personalDataService.addPersonalData(new PersonalData("firstName_4", "lastName_4", "personalNumber_4", "profilePicturePath_4"));
		personalDataService.addPersonalData(new PersonalData("firstName_5", "lastName_5", "personalNumber_5", "profilePicturePath_5"));
	}

	@After
	public void afterPersonalData() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "personal_data");
	}

	@Test
	public void getPersonalData() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/personaldata").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getPersonalDataId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/personaldata/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.firstName", equalTo("firstName_5")))
		.andExpect(jsonPath("$.lastName", equalTo("lastName_5")))
		.andExpect(jsonPath("$.personalNumber", equalTo("personalNumber_5")))
		.andExpect(jsonPath("$.profilePicturePath", equalTo("profilePicturePath_5")));
	}
}
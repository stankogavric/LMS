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
import App.services.TeachingTypeService;

import App.models.TeachingType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTeachingTypeController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TeachingTypeService teachingTypeService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTeachingType() {
		teachingTypeService.addTeachingType(new TeachingType("name_1"));
		teachingTypeService.addTeachingType(new TeachingType("name_2"));
		teachingTypeService.addTeachingType(new TeachingType("name_3"));
		teachingTypeService.addTeachingType(new TeachingType("name_4"));
		teachingTypeService.addTeachingType(new TeachingType("name_5"));
	}

	@After
	public void afterTeachingType() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "teaching_type");
	}

	@Test
	public void getTeachingType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/teachingtype").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTeachingTypeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachingtype/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")));
	}
}
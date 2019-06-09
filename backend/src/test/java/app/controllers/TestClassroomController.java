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
import App.services.ClassroomService;

import App.models.Faculty;
import App.models.Classroom;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestClassroomController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ClassroomService classroomService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupClassroom() {
		classroomService.addClassroom(new Classroom("name_1", "type_1", 299, new Faculty("name_1", null, null, null, null, "description_1", null, null, null)));
		classroomService.addClassroom(new Classroom("name_2", "type_2", 313, new Faculty("name_2", null, null, null, null, "description_2", null, null, null)));
		classroomService.addClassroom(new Classroom("name_3", "type_3", 18, new Faculty("name_3", null, null, null, null, "description_3", null, null, null)));
		classroomService.addClassroom(new Classroom("name_4", "type_4", 888, new Faculty("name_4", null, null, null, null, "description_4", null, null, null)));
		classroomService.addClassroom(new Classroom("name_5", "type_5", 144, new Faculty("name_5", null, null, null, null, "description_5", null, null, null)));
	}

	@After
	public void afterClassroom() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "classroom", "faculty");
	}

	@Test
	public void getClassroom() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/classroom").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getClassroomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.type", equalTo("type_5")))
		.andExpect(jsonPath("$.capacity", equalTo(144)))
		.andExpect(jsonPath("$.faculty.name", equalTo("name_5")))
		.andExpect(jsonPath("$.faculty.university", equalTo(null)))
		.andExpect(jsonPath("$.faculty.address", equalTo(null)))
		.andExpect(jsonPath("$.faculty.dean", equalTo(null)))
		.andExpect(jsonPath("$.faculty.description", equalTo("description_5")));
	}
}
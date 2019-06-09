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
import App.services.TeacherRealizationService;

import App.models.TeachingType;
import App.models.TeacherRealization;
import App.models.Teacher;
import App.models.SubjectRealization;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTeacherRealizationController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TeacherRealizationService teacherRealizationService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTeacherRealization() {
		teacherRealizationService.addTeacherRealization(new TeacherRealization(788, new TeachingType("name_1"), new Teacher("biography_1", null, null, null, null, false), new SubjectRealization(null, null, null, null, null, null)));
		teacherRealizationService.addTeacherRealization(new TeacherRealization(234, new TeachingType("name_2"), new Teacher("biography_2", null, null, null, null, false), new SubjectRealization(null, null, null, null, null, null)));
		teacherRealizationService.addTeacherRealization(new TeacherRealization(68, new TeachingType("name_3"), new Teacher("biography_3", null, null, null, null, false), new SubjectRealization(null, null, null, null, null, null)));
		teacherRealizationService.addTeacherRealization(new TeacherRealization(112, new TeachingType("name_4"), new Teacher("biography_4", null, null, null, null, false), new SubjectRealization(null, null, null, null, null, null)));
		teacherRealizationService.addTeacherRealization(new TeacherRealization(570, new TeachingType("name_5"), new Teacher("biography_5", null, null, null, null, false), new SubjectRealization(null, null, null, null, null, null)));
	}

	@After
	public void afterTeacherRealization() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "teacher_realization", "teaching_type", "teacher", "subject_realization");
	}

	@Test
	public void getTeacherRealization() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/teacherrealization").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTeacherRealizationId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacherrealization/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.numberOfClasses", equalTo(570)))
		.andExpect(jsonPath("$.teachingType.name", equalTo("name_5")))
		.andExpect(jsonPath("$.teacher.biography", equalTo("biography_5")))
		.andExpect(jsonPath("$.teacher.address", equalTo(null)))
		.andExpect(jsonPath("$.teacher.accountData", equalTo(null)))
		.andExpect(jsonPath("$.teacher.personalData", equalTo(null)))
		.andExpect(jsonPath("$.teacher.deleted", equalTo(false)))
		.andExpect(jsonPath("$.subjectRealization.subject", equalTo(null)))
		.andExpect(jsonPath("$.subjectRealization.yearOfStudy", equalTo(null)));
	}
}
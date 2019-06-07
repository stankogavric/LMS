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
import App.services.SubjectAttendanceService;

import App.models.Student;
import App.models.SubjectAttendance;
import App.models.SubjectRealization;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestSubjectAttendanceController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	SubjectAttendanceService subjectAttendanceService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupSubjectAttendance() {
		subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(724, new Student(null, null, null, null, null, false, 876), new SubjectRealization(null, null, null, null, null, null)));
		subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(676, new Student(null, null, null, null, null, false, 202), new SubjectRealization(null, null, null, null, null, null)));
		subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(933, new Student(null, null, null, null, null, false, 356), new SubjectRealization(null, null, null, null, null, null)));
		subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(632, new Student(null, null, null, null, null, false, 101), new SubjectRealization(null, null, null, null, null, null)));
		subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(122, new Student(null, null, null, null, null, false, 629), new SubjectRealization(null, null, null, null, null, null)));
	}

	@After
	public void afterSubjectAttendance() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "subject_attendance", "student", "subject_realization");
	}

	@Test
	public void getSubjectAttendance() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/subjectattendance").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getSubjectAttendanceId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/subjectattendance/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.finalGrade", equalTo(122)))
		.andExpect(jsonPath("$.student.address", equalTo(null)))
		.andExpect(jsonPath("$.student.accountData", equalTo(null)))
		.andExpect(jsonPath("$.student.personalData", equalTo(null)))
		.andExpect(jsonPath("$.student.deleted", equalTo(false)))
		.andExpect(jsonPath("$.student.yearOfStudy", equalTo(629)))
		.andExpect(jsonPath("$.subjectRealization.subject", equalTo(null)))
		.andExpect(jsonPath("$.subjectRealization.yearOfStudy", equalTo(null)));
	}
}
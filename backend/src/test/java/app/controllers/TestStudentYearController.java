package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import java.text.ParseException;import java.util.Arrays;
import java.util.HashSet;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import com.jayway.jsonpath.JsonPath;
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
import App.services.StudentYearService;

import App.models.ExamRealization;
import App.models.StudentYear;
import App.models.YearOfStudy;
import App.models.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestStudentYearController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	StudentYearService studentYearService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupStudentYear() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		studentYearService.addStudentYear(new StudentYear(dt.parse("2023-05-26 00:00:00"), "numIndex_1", new YearOfStudy(552, dt.parse("2022-01-05 00:00:00"), dt.parse("2017-05-24 00:00:00"), null, null), new Student(null, null, null, null, null, false, 554), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(514, "note_1", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2019-02-22 00:00:00"), "numIndex_2", new YearOfStudy(601, dt.parse("2024-11-01 00:00:00"), dt.parse("2009-04-02 00:00:00"), null, null), new Student(null, null, null, null, null, false, 347), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(739, "note_2", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2017-03-17 00:00:00"), "numIndex_3", new YearOfStudy(672, dt.parse("2024-03-00 00:00:00"), dt.parse("2012-07-08 00:00:00"), null, null), new Student(null, null, null, null, null, false, 695), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(916, "note_3", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2022-07-12 00:00:00"), "numIndex_4", new YearOfStudy(344, dt.parse("2024-06-24 00:00:00"), dt.parse("2021-04-25 00:00:00"), null, null), new Student(null, null, null, null, null, false, 558), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(14, "note_4", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2015-02-17 00:00:00"), "numIndex_5", new YearOfStudy(571, dt.parse("2017-02-26 00:00:00"), dt.parse("2023-06-08 00:00:00"), null, null), new Student(null, null, null, null, null, false, 725), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(333, "note_5", null, null)))));
	}

	@After
	public void afterStudentYear() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "student_year", "year_of_study", "student", "exam_realization");
	}

	@Test
	public void getStudentYear() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studentyear").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getStudentYearId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/studentyear/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.numIndex", equalTo("numIndex_5")))
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(571)))
		.andExpect(jsonPath("$.student.address", equalTo(null)))
		.andExpect(jsonPath("$.student.accountData", equalTo(null)))
		.andExpect(jsonPath("$.student.personalData", equalTo(null)))
		.andExpect(jsonPath("$.student.deleted", equalTo(false)))
		.andExpect(jsonPath("$.student.yearOfStudy", equalTo(725))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2015-02-17 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.enrolmentDate")));
		assertEquals(dt.parse("2017-02-26 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2023-06-08 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
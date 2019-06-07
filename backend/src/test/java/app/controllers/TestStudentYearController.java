package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import java.text.ParseException;
import com.jayway.jsonpath.JsonPath;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import java.util.TimeZone;import java.util.Arrays;
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
import App.services.StudentYearService;

import App.models.Student;
import App.models.ExamRealization;
import App.models.YearOfStudy;
import App.models.StudentYear;

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
		studentYearService.addStudentYear(new StudentYear(dt.parse("2008-01-27 00:00:00"), "numIndex_1", new YearOfStudy(278, dt.parse("2018-11-19 00:00:00"), dt.parse("2015-02-24 00:00:00"), null, null), new Student(null, null, null, null, null, false, 363), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(632, "note_1", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2017-04-03 00:00:00"), "numIndex_2", new YearOfStudy(53, dt.parse("2016-03-27 00:00:00"), dt.parse("2022-05-14 00:00:00"), null, null), new Student(null, null, null, null, null, false, 881), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(633, "note_2", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2013-03-28 00:00:00"), "numIndex_3", new YearOfStudy(926, dt.parse("2011-11-11 00:00:00"), dt.parse("2013-00-20 00:00:00"), null, null), new Student(null, null, null, null, null, false, 572), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(639, "note_3", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2011-04-18 00:00:00"), "numIndex_4", new YearOfStudy(450, dt.parse("2017-07-14 00:00:00"), dt.parse("2010-06-17 00:00:00"), null, null), new Student(null, null, null, null, null, false, 646), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(409, "note_4", null, null)))));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2010-08-16 00:00:00"), "numIndex_5", new YearOfStudy(469, dt.parse("2010-05-06 00:00:00"), dt.parse("2021-11-22 00:00:00"), null, null), new Student(null, null, null, null, null, false, 568), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(960, "note_5", null, null)))));
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
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(469)))
		.andExpect(jsonPath("$.student.address", equalTo(null)))
		.andExpect(jsonPath("$.student.accountData", equalTo(null)))
		.andExpect(jsonPath("$.student.personalData", equalTo(null)))
		.andExpect(jsonPath("$.student.deleted", equalTo(false)))
		.andExpect(jsonPath("$.student.yearOfStudy", equalTo(568))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2010-08-16 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.enrolmentDate")));
		assertEquals(dt.parse("2010-05-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2021-11-22 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
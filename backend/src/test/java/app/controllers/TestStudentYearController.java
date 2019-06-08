package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashSet;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.jayway.jsonpath.JsonPath;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
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

import App.models.StudentYear;
import App.models.ExamRealization;
import App.models.Student;
import App.models.YearOfStudy;
import App.models.Dissertation;

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
		studentYearService.addStudentYear(new StudentYear(dt.parse("2015-07-17 00:00:00"), "numIndex_1", new YearOfStudy(62, dt.parse("2018-00-21 00:00:00"), dt.parse("2011-00-25 00:00:00"), null, null), new Student(null, null, null, null, null, false, 463), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(810, "note_1", null, null))), new Dissertation("title_1", dt.parse("2010-09-07 00:00:00"), dt.parse("2006-06-25 00:00:00"), null, null, null)));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2012-01-26 00:00:00"), "numIndex_2", new YearOfStudy(472, dt.parse("2023-00-23 00:00:00"), dt.parse("2024-03-27 00:00:00"), null, null), new Student(null, null, null, null, null, false, 169), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(667, "note_2", null, null))), new Dissertation("title_2", dt.parse("2022-03-09 00:00:00"), dt.parse("2021-09-27 00:00:00"), null, null, null)));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2014-09-20 00:00:00"), "numIndex_3", new YearOfStudy(222, dt.parse("2013-04-24 00:00:00"), dt.parse("2022-11-20 00:00:00"), null, null), new Student(null, null, null, null, null, false, 602), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(663, "note_3", null, null))), new Dissertation("title_3", dt.parse("2011-09-08 00:00:00"), dt.parse("2023-10-17 00:00:00"), null, null, null)));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2024-06-27 00:00:00"), "numIndex_4", new YearOfStudy(643, dt.parse("2023-00-19 00:00:00"), dt.parse("2009-06-25 00:00:00"), null, null), new Student(null, null, null, null, null, false, 940), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(502, "note_4", null, null))), new Dissertation("title_4", dt.parse("2008-10-01 00:00:00"), dt.parse("2006-10-01 00:00:00"), null, null, null)));
		studentYearService.addStudentYear(new StudentYear(dt.parse("2019-06-15 00:00:00"), "numIndex_5", new YearOfStudy(220, dt.parse("2008-07-05 00:00:00"), dt.parse("2007-01-20 00:00:00"), null, null), new Student(null, null, null, null, null, false, 9), new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(98, "note_5", null, null))), new Dissertation("title_5", dt.parse("2011-09-02 00:00:00"), dt.parse("2012-06-07 00:00:00"), null, null, null)));
	}

	@After
	public void afterStudentYear() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "student_year", "year_of_study", "student", "exam_realization", "dissertation");
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
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(220)))
		.andExpect(jsonPath("$.student.address", equalTo(null)))
		.andExpect(jsonPath("$.student.accountData", equalTo(null)))
		.andExpect(jsonPath("$.student.personalData", equalTo(null)))
		.andExpect(jsonPath("$.student.deleted", equalTo(false)))
		.andExpect(jsonPath("$.student.yearOfStudy", equalTo(9)))
		.andExpect(jsonPath("$.dissertation.title", equalTo("title_5")))
		.andExpect(jsonPath("$.dissertation.mentor", equalTo(null)))
		.andExpect(jsonPath("$.dissertation.studentYear", equalTo(null)))
		.andExpect(jsonPath("$.dissertation.file", equalTo(null))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2019-06-15 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.enrolmentDate")));
		assertEquals(dt.parse("2008-07-05 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2007-01-20 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));
		assertEquals(dt.parse("2011-09-02 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.dissertation.applicationDate")));
		assertEquals(dt.parse("2012-06-07 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.dissertation.defenseDate")));

	}
}
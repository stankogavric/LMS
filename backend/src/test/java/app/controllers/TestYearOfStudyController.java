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
import App.services.YearOfStudyService;

import App.models.YearOfStudy;
import App.models.SubjectRealization;
import App.models.StudyProgram;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestYearOfStudyController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	YearOfStudyService yearOfStudyService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupYearOfStudy() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		yearOfStudyService.addYearOfStudy(new YearOfStudy(205, dt.parse("2019-07-18 00:00:00"), dt.parse("2007-03-06 00:00:00"), new HashSet<SubjectRealization>(Arrays.asList(new SubjectRealization(null, null, null, null, null, null))), new StudyProgram("name_1", null, null, null, "description_1", false)));
		yearOfStudyService.addYearOfStudy(new YearOfStudy(921, dt.parse("2020-11-21 00:00:00"), dt.parse("2013-06-28 00:00:00"), new HashSet<SubjectRealization>(Arrays.asList(new SubjectRealization(null, null, null, null, null, null))), new StudyProgram("name_2", null, null, null, "description_2", false)));
		yearOfStudyService.addYearOfStudy(new YearOfStudy(207, dt.parse("2022-01-02 00:00:00"), dt.parse("2011-09-20 00:00:00"), new HashSet<SubjectRealization>(Arrays.asList(new SubjectRealization(null, null, null, null, null, null))), new StudyProgram("name_3", null, null, null, "description_3", false)));
		yearOfStudyService.addYearOfStudy(new YearOfStudy(603, dt.parse("2013-07-13 00:00:00"), dt.parse("2008-11-00 00:00:00"), new HashSet<SubjectRealization>(Arrays.asList(new SubjectRealization(null, null, null, null, null, null))), new StudyProgram("name_4", null, null, null, "description_4", false)));
		yearOfStudyService.addYearOfStudy(new YearOfStudy(449, dt.parse("2008-08-04 00:00:00"), dt.parse("2011-06-21 00:00:00"), new HashSet<SubjectRealization>(Arrays.asList(new SubjectRealization(null, null, null, null, null, null))), new StudyProgram("name_5", null, null, null, "description_5", false)));
	}

	@After
	public void afterYearOfStudy() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "year_of_study", "subject_realization", "study_program");
	}

	@Test
	public void getYearOfStudy() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/yearofstudy").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getYearOfStudyId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/yearofstudy/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.year", equalTo(449))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2008-08-04 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.startDate")));
		assertEquals(dt.parse("2011-06-21 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.endDate")));

	}
}
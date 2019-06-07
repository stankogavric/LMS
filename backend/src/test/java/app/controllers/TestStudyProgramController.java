package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import com.jayway.jsonpath.JsonPath;import java.util.Arrays;
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
import App.services.StudyProgramService;

import App.models.YearOfStudy;
import App.models.StudyProgram;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestStudyProgramController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	StudyProgramService studyProgramService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupStudyProgram() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		studyProgramService.addStudyProgram(new StudyProgram("name_1", new HashSet<YearOfStudy>(Arrays.asList(new YearOfStudy(813, dt.parse("2016-01-04 00:00:00"), dt.parse("2020-05-09 00:00:00"), null, null))), null, null, "description_1", false));
		studyProgramService.addStudyProgram(new StudyProgram("name_2", new HashSet<YearOfStudy>(Arrays.asList(new YearOfStudy(407, dt.parse("2012-05-07 00:00:00"), dt.parse("2018-11-13 00:00:00"), null, null))), null, null, "description_2", false));
		studyProgramService.addStudyProgram(new StudyProgram("name_3", new HashSet<YearOfStudy>(Arrays.asList(new YearOfStudy(241, dt.parse("2019-06-25 00:00:00"), dt.parse("2011-08-20 00:00:00"), null, null))), null, null, "description_3", false));
		studyProgramService.addStudyProgram(new StudyProgram("name_4", new HashSet<YearOfStudy>(Arrays.asList(new YearOfStudy(886, dt.parse("2023-05-03 00:00:00"), dt.parse("2005-08-21 00:00:00"), null, null))), null, null, "description_4", false));
		studyProgramService.addStudyProgram(new StudyProgram("name_5", new HashSet<YearOfStudy>(Arrays.asList(new YearOfStudy(270, dt.parse("2024-08-05 00:00:00"), dt.parse("2011-05-13 00:00:00"), null, null))), null, null, "description_5", false));
	}

	@After
	public void afterStudyProgram() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "study_program", "year_of_study", "teacher", "faculty");
	}

	@Test
	public void getStudyProgram() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studyprogram").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getStudyProgramId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/studyprogram/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.headTeacher", equalTo(null)))
		.andExpect(jsonPath("$.faculty", equalTo(null)))
		.andExpect(jsonPath("$.description", equalTo("description_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2024-08-05 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearsOfStudy[0].startDate")));
		assertEquals(dt.parse("2011-05-13 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearsOfStudy[0].endDate")));

	}
}
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
import App.services.SubjectService;

import App.models.Topic;
import App.models.YearOfStudy;
import App.models.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestSubjectController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	SubjectService subjectService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupSubject() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		subjectService.addSubject(new Subject("name_1", 436, false, 168, 73, 676, 81, 99, new HashSet<Topic>(Arrays.asList(new Topic("description_1", 441, "iconPath_1", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 160, false, 60, 868, 304, 871, 325, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 667, false, 7, 228, 64, 146, 424, null, null, null, null, false))), new YearOfStudy(39, dt.parse("2024-05-17 00:00:00"), dt.parse("2018-05-14 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_2", 412, false, 874, 256, 561, 606, 984, new HashSet<Topic>(Arrays.asList(new Topic("description_2", 950, "iconPath_2", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 375, false, 73, 809, 886, 359, 842, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 923, false, 224, 472, 343, 163, 72, null, null, null, null, false))), new YearOfStudy(932, dt.parse("2005-04-19 00:00:00"), dt.parse("2013-06-08 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_3", 145, false, 689, 83, 91, 141, 48, new HashSet<Topic>(Arrays.asList(new Topic("description_3", 558, "iconPath_3", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 440, false, 645, 977, 473, 901, 994, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 585, false, 856, 199, 984, 340, 109, null, null, null, null, false))), new YearOfStudy(388, dt.parse("2019-07-21 00:00:00"), dt.parse("2023-05-27 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_4", 961, false, 48, 885, 169, 357, 10, new HashSet<Topic>(Arrays.asList(new Topic("description_4", 69, "iconPath_4", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 192, false, 532, 78, 590, 334, 772, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 164, false, 396, 104, 983, 286, 48, null, null, null, null, false))), new YearOfStudy(777, dt.parse("2023-00-05 00:00:00"), dt.parse("2012-11-05 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_5", 969, false, 695, 230, 93, 49, 806, new HashSet<Topic>(Arrays.asList(new Topic("description_5", 788, "iconPath_5", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 203, false, 149, 28, 654, 518, 589, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 8, false, 690, 28, 345, 103, 725, null, null, null, null, false))), new YearOfStudy(99, dt.parse("2020-02-06 00:00:00"), dt.parse("2005-08-09 00:00:00"), null, null), false));
	}

	@After
	public void afterSubject() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "subject", "topic", "subject", "subject", "year_of_study");
	}

	@Test
	public void getSubject() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/subject").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getSubjectId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/subject/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.ects", equalTo(969)))
		.andExpect(jsonPath("$.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.lecturesNum", equalTo(695)))
		.andExpect(jsonPath("$.exercisesNum", equalTo(230)))
		.andExpect(jsonPath("$.otherActivitiesNum", equalTo(93)))
		.andExpect(jsonPath("$.researchPaper", equalTo(49)))
		.andExpect(jsonPath("$.otherClasses", equalTo(806)))
		.andExpect(jsonPath("$.prerequisites[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisites[0].ects", equalTo(203)))
		.andExpect(jsonPath("$.prerequisites[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisites[0].lecturesNum", equalTo(149)))
		.andExpect(jsonPath("$.prerequisites[0].exercisesNum", equalTo(28)))
		.andExpect(jsonPath("$.prerequisites[0].otherActivitiesNum", equalTo(654)))
		.andExpect(jsonPath("$.prerequisites[0].researchPaper", equalTo(518)))
		.andExpect(jsonPath("$.prerequisites[0].otherClasses", equalTo(589)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisiteFor[0].ects", equalTo(8)))
		.andExpect(jsonPath("$.prerequisiteFor[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].lecturesNum", equalTo(690)))
		.andExpect(jsonPath("$.prerequisiteFor[0].exercisesNum", equalTo(28)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherActivitiesNum", equalTo(345)))
		.andExpect(jsonPath("$.prerequisiteFor[0].researchPaper", equalTo(103)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherClasses", equalTo(725)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2020-02-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2005-08-09 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
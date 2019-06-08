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
import App.services.SubjectService;

import App.models.Subject;
import App.models.Topic;
import App.models.YearOfStudy;

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
		subjectService.addSubject(new Subject("name_1", 502, false, 621, 298, 72, 650, 962, new HashSet<Topic>(Arrays.asList(new Topic("description_1", 587, "iconPath_1", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 620, false, 881, 818, 181, 895, 699, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 222, false, 714, 622, 323, 591, 975, null, null, null, null, false))), new YearOfStudy(479, dt.parse("2005-09-28 00:00:00"), dt.parse("2008-00-02 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_2", 957, false, 710, 561, 758, 466, 222, new HashSet<Topic>(Arrays.asList(new Topic("description_2", 904, "iconPath_2", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 650, false, 959, 440, 215, 279, 443, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 218, false, 434, 984, 70, 864, 203, null, null, null, null, false))), new YearOfStudy(306, dt.parse("2011-07-07 00:00:00"), dt.parse("2022-05-08 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_3", 105, false, 875, 855, 539, 281, 709, new HashSet<Topic>(Arrays.asList(new Topic("description_3", 774, "iconPath_3", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 760, false, 867, 57, 763, 452, 329, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 321, false, 135, 348, 669, 447, 268, null, null, null, null, false))), new YearOfStudy(821, dt.parse("2015-04-04 00:00:00"), dt.parse("2006-01-17 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_4", 218, false, 186, 431, 233, 779, 838, new HashSet<Topic>(Arrays.asList(new Topic("description_4", 625, "iconPath_4", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 873, false, 392, 180, 141, 998, 371, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 423, false, 911, 601, 675, 306, 320, null, null, null, null, false))), new YearOfStudy(84, dt.parse("2006-02-19 00:00:00"), dt.parse("2014-05-18 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_5", 15, false, 167, 171, 553, 290, 631, new HashSet<Topic>(Arrays.asList(new Topic("description_5", 392, "iconPath_5", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 846, false, 497, 281, 110, 637, 421, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 908, false, 713, 752, 797, 390, 239, null, null, null, null, false))), new YearOfStudy(866, dt.parse("2024-10-06 00:00:00"), dt.parse("2006-02-06 00:00:00"), null, null), false));
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
		.andExpect(jsonPath("$.ects", equalTo(15)))
		.andExpect(jsonPath("$.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.lecturesNum", equalTo(167)))
		.andExpect(jsonPath("$.exercisesNum", equalTo(171)))
		.andExpect(jsonPath("$.otherActivitiesNum", equalTo(553)))
		.andExpect(jsonPath("$.researchPaper", equalTo(290)))
		.andExpect(jsonPath("$.otherClasses", equalTo(631)))
		.andExpect(jsonPath("$.prerequisites[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisites[0].ects", equalTo(846)))
		.andExpect(jsonPath("$.prerequisites[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisites[0].lecturesNum", equalTo(497)))
		.andExpect(jsonPath("$.prerequisites[0].exercisesNum", equalTo(281)))
		.andExpect(jsonPath("$.prerequisites[0].otherActivitiesNum", equalTo(110)))
		.andExpect(jsonPath("$.prerequisites[0].researchPaper", equalTo(637)))
		.andExpect(jsonPath("$.prerequisites[0].otherClasses", equalTo(421)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisiteFor[0].ects", equalTo(908)))
		.andExpect(jsonPath("$.prerequisiteFor[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].lecturesNum", equalTo(713)))
		.andExpect(jsonPath("$.prerequisiteFor[0].exercisesNum", equalTo(752)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherActivitiesNum", equalTo(797)))
		.andExpect(jsonPath("$.prerequisiteFor[0].researchPaper", equalTo(390)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherClasses", equalTo(239)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2024-10-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2006-02-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
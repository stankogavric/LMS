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
import App.services.SubjectRealizationService;

import App.models.TeacherRealization;
import App.models.YearOfStudy;
import App.models.SubjectRealization;
import App.models.TeachingTerm;
import App.models.Exam;
import App.models.TeachingMaterial;
import App.models.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestSubjectRealizationController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	SubjectRealizationService subjectRealizationService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupSubjectRealization() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(640, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm(dt.parse("2006-11-17 00:00:00"), dt.parse("2014-00-02 00:00:00"), null))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2020-03-18 00:00:00"), dt.parse("2015-07-07 00:00:00"), 727, 731, null, null, null, null))), new Subject("name_1", 441, false, 977, 88, 419, 847, 31, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_1", null, dt.parse("2011-06-11 00:00:00"), null, null))), new YearOfStudy(0, dt.parse("2008-00-25 00:00:00"), dt.parse("2024-10-16 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(526, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm(dt.parse("2012-03-15 00:00:00"), dt.parse("2025-10-12 00:00:00"), null))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2018-01-17 00:00:00"), dt.parse("2023-08-27 00:00:00"), 963, 576, null, null, null, null))), new Subject("name_2", 298, false, 248, 783, 419, 651, 789, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_2", null, dt.parse("2021-04-15 00:00:00"), null, null))), new YearOfStudy(107, dt.parse("2018-07-02 00:00:00"), dt.parse("2018-07-04 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(671, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm(dt.parse("2007-01-12 00:00:00"), dt.parse("2015-09-14 00:00:00"), null))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2006-11-28 00:00:00"), dt.parse("2016-07-08 00:00:00"), 754, 704, null, null, null, null))), new Subject("name_3", 992, false, 910, 974, 796, 877, 714, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_3", null, dt.parse("2007-01-22 00:00:00"), null, null))), new YearOfStudy(930, dt.parse("2011-10-15 00:00:00"), dt.parse("2011-09-05 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(935, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm(dt.parse("2013-06-23 00:00:00"), dt.parse("2005-00-16 00:00:00"), null))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2023-03-07 00:00:00"), dt.parse("2022-11-23 00:00:00"), 553, 546, null, null, null, null))), new Subject("name_4", 726, false, 982, 915, 72, 444, 709, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_4", null, dt.parse("2023-09-03 00:00:00"), null, null))), new YearOfStudy(506, dt.parse("2023-09-01 00:00:00"), dt.parse("2010-06-19 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(420, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm(dt.parse("2005-08-04 00:00:00"), dt.parse("2016-05-28 00:00:00"), null))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2016-08-12 00:00:00"), dt.parse("2023-07-17 00:00:00"), 757, 701, null, null, null, null))), new Subject("name_5", 853, false, 287, 332, 601, 576, 996, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_5", null, dt.parse("2015-03-28 00:00:00"), null, null))), new YearOfStudy(121, dt.parse("2018-10-15 00:00:00"), dt.parse("2021-08-01 00:00:00"), null, null)));
	}

	@After
	public void afterSubjectRealization() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "subject_realization", "teacher_realization", "teaching_term", "exam", "subject", "teaching_material", "year_of_study");
	}

	@Test
	public void getSubjectRealization() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/subjectrealization").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getSubjectRealizationId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/subjectrealization/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.subject.name", equalTo("name_5")))
		.andExpect(jsonPath("$.subject.ects", equalTo(853)))
		.andExpect(jsonPath("$.subject.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.subject.lecturesNum", equalTo(287)))
		.andExpect(jsonPath("$.subject.exercisesNum", equalTo(332)))
		.andExpect(jsonPath("$.subject.otherActivitiesNum", equalTo(601)))
		.andExpect(jsonPath("$.subject.researchPaper", equalTo(576)))
		.andExpect(jsonPath("$.subject.otherClasses", equalTo(996)))
		.andExpect(jsonPath("$.subject.prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.subject.prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.subject.deleted", equalTo(false)))
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(121))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2005-08-04 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].startTime")));
		assertEquals(dt.parse("2016-05-28 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].endTime")));
		assertEquals(dt.parse("2016-08-12 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].startTime")));
		assertEquals(dt.parse("2023-07-17 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].endTime")));
		assertEquals(dt.parse("2015-03-28 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingMaterials[0].publicationDate")));
		assertEquals(dt.parse("2018-10-15 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2021-08-01 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
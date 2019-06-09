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
import App.services.SubjectRealizationService;

import App.models.TeachingMaterial;
import App.models.Subject;
import App.models.SubjectRealization;
import App.models.YearOfStudy;
import App.models.Exam;
import App.models.TeachingTerm;
import App.models.TeacherRealization;

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
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(24, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_1", dt.parse("2014-02-24 00:00:00"), dt.parse("2021-05-15 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2005-10-15 00:00:00"), dt.parse("2017-01-24 00:00:00"), 332, 556, null, null, null, null))), new Subject("name_1", 302, false, 833, 662, 901, 868, 814, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_1", null, dt.parse("2020-04-00 00:00:00"), null, null))), new YearOfStudy(117, dt.parse("2024-06-20 00:00:00"), dt.parse("2014-02-04 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(556, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_2", dt.parse("2006-07-10 00:00:00"), dt.parse("2018-02-00 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2015-02-16 00:00:00"), dt.parse("2010-10-04 00:00:00"), 673, 207, null, null, null, null))), new Subject("name_2", 87, false, 35, 481, 254, 687, 805, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_2", null, dt.parse("2005-03-00 00:00:00"), null, null))), new YearOfStudy(52, dt.parse("2023-08-15 00:00:00"), dt.parse("2016-10-07 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(637, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_3", dt.parse("2017-06-12 00:00:00"), dt.parse("2017-10-19 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2009-11-05 00:00:00"), dt.parse("2005-05-15 00:00:00"), 373, 203, null, null, null, null))), new Subject("name_3", 764, false, 824, 468, 91, 140, 869, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_3", null, dt.parse("2019-10-21 00:00:00"), null, null))), new YearOfStudy(490, dt.parse("2017-00-15 00:00:00"), dt.parse("2021-08-03 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(436, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_4", dt.parse("2017-01-07 00:00:00"), dt.parse("2012-06-28 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2016-01-03 00:00:00"), dt.parse("2019-03-22 00:00:00"), 994, 956, null, null, null, null))), new Subject("name_4", 154, false, 482, 268, 754, 376, 239, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_4", null, dt.parse("2008-08-07 00:00:00"), null, null))), new YearOfStudy(380, dt.parse("2012-11-17 00:00:00"), dt.parse("2011-11-16 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(707, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_5", dt.parse("2020-11-00 00:00:00"), dt.parse("2010-01-00 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2024-08-26 00:00:00"), dt.parse("2020-08-12 00:00:00"), 774, 858, null, null, null, null))), new Subject("name_5", 377, false, 218, 482, 521, 406, 960, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_5", null, dt.parse("2019-10-08 00:00:00"), null, null))), new YearOfStudy(303, dt.parse("2024-01-25 00:00:00"), dt.parse("2008-06-06 00:00:00"), null, null)));
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
		.andExpect(jsonPath("$.subject.ects", equalTo(377)))
		.andExpect(jsonPath("$.subject.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.subject.lecturesNum", equalTo(218)))
		.andExpect(jsonPath("$.subject.exercisesNum", equalTo(482)))
		.andExpect(jsonPath("$.subject.otherActivitiesNum", equalTo(521)))
		.andExpect(jsonPath("$.subject.researchPaper", equalTo(406)))
		.andExpect(jsonPath("$.subject.otherClasses", equalTo(960)))
		.andExpect(jsonPath("$.subject.prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.subject.prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.subject.deleted", equalTo(false)))
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(303))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2020-11-00 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].startTime")));
		assertEquals(dt.parse("2010-01-00 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].endTime")));
		assertEquals(dt.parse("2024-08-26 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].startTime")));
		assertEquals(dt.parse("2020-08-12 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].endTime")));
		assertEquals(dt.parse("2019-10-08 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingMaterials[0].publicationDate")));
		assertEquals(dt.parse("2024-01-25 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2008-06-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
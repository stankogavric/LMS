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
import App.services.SubjectRealizationService;

import App.models.SubjectRealization;
import App.models.TeachingTerm;
import App.models.TeachingMaterial;
import App.models.YearOfStudy;
import App.models.TeacherRealization;
import App.models.Exam;
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
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(914, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_1", dt.parse("2025-10-01 00:00:00"), dt.parse("2018-05-05 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2018-07-19 00:00:00"), dt.parse("2005-01-08 00:00:00"), 354, 146, null, null, null, null))), new Subject("name_1", 57, false, 308, 519, 672, 171, 866, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_1", null, dt.parse("2015-07-17 00:00:00"), null, null))), new YearOfStudy(226, dt.parse("2023-09-17 00:00:00"), dt.parse("2020-00-25 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(243, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_2", dt.parse("2024-06-27 00:00:00"), dt.parse("2024-09-26 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2006-04-04 00:00:00"), dt.parse("2009-11-12 00:00:00"), 219, 387, null, null, null, null))), new Subject("name_2", 919, false, 602, 909, 360, 863, 430, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_2", null, dt.parse("2013-06-16 00:00:00"), null, null))), new YearOfStudy(741, dt.parse("2009-01-05 00:00:00"), dt.parse("2006-04-02 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(759, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_3", dt.parse("2007-02-16 00:00:00"), dt.parse("2008-10-24 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2024-08-20 00:00:00"), dt.parse("2019-02-04 00:00:00"), 855, 904, null, null, null, null))), new Subject("name_3", 341, false, 295, 259, 590, 441, 749, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_3", null, dt.parse("2015-00-25 00:00:00"), null, null))), new YearOfStudy(113, dt.parse("2019-07-14 00:00:00"), dt.parse("2018-01-21 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(207, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_4", dt.parse("2018-04-00 00:00:00"), dt.parse("2017-11-16 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2012-03-03 00:00:00"), dt.parse("2015-10-18 00:00:00"), 308, 667, null, null, null, null))), new Subject("name_4", 837, false, 586, 600, 243, 6, 563, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_4", null, dt.parse("2019-10-09 00:00:00"), null, null))), new YearOfStudy(212, dt.parse("2014-00-05 00:00:00"), dt.parse("2022-11-11 00:00:00"), null, null)));
		subjectRealizationService.addSubjectRealization(new SubjectRealization(new HashSet<TeacherRealization>(Arrays.asList(new TeacherRealization(535, null, null, null))), new HashSet<TeachingTerm>(Arrays.asList(new TeachingTerm("day_5", dt.parse("2018-00-16 00:00:00"), dt.parse("2007-05-26 00:00:00"), null, null, false))), new HashSet<Exam>(Arrays.asList(new Exam(dt.parse("2011-01-08 00:00:00"), dt.parse("2017-07-20 00:00:00"), 322, 904, null, null, null, null))), new Subject("name_5", 452, false, 916, 878, 862, 709, 256, null, null, null, null, false), new HashSet<TeachingMaterial>(Arrays.asList(new TeachingMaterial("name_5", null, dt.parse("2009-02-10 00:00:00"), null, null))), new YearOfStudy(457, dt.parse("2013-03-14 00:00:00"), dt.parse("2010-02-17 00:00:00"), null, null)));
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
		.andExpect(jsonPath("$.subject.ects", equalTo(452)))
		.andExpect(jsonPath("$.subject.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.subject.lecturesNum", equalTo(916)))
		.andExpect(jsonPath("$.subject.exercisesNum", equalTo(878)))
		.andExpect(jsonPath("$.subject.otherActivitiesNum", equalTo(862)))
		.andExpect(jsonPath("$.subject.researchPaper", equalTo(709)))
		.andExpect(jsonPath("$.subject.otherClasses", equalTo(256)))
		.andExpect(jsonPath("$.subject.prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.subject.prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.subject.deleted", equalTo(false)))
		.andExpect(jsonPath("$.yearOfStudy.year", equalTo(457))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2018-00-16 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].startTime")));
		assertEquals(dt.parse("2007-05-26 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingTerms[0].endTime")));
		assertEquals(dt.parse("2011-01-08 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].startTime")));
		assertEquals(dt.parse("2017-07-20 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.exams[0].endTime")));
		assertEquals(dt.parse("2009-02-10 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.teachingMaterials[0].publicationDate")));
		assertEquals(dt.parse("2013-03-14 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2010-02-17 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
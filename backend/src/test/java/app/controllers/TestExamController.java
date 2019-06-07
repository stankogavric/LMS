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
import App.services.ExamService;

import App.models.ExamRealization;
import App.models.ExamTopic;
import App.models.Exam;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestExamController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ExamService examService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupExam() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		examService.addExam(new Exam(dt.parse("2010-00-27 00:00:00"), dt.parse("2014-02-17 00:00:00"), 60, 189, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(151, "note_1", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_1", null)))));
		examService.addExam(new Exam(dt.parse("2022-09-05 00:00:00"), dt.parse("2010-00-00 00:00:00"), 943, 500, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(38, "note_2", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_2", null)))));
		examService.addExam(new Exam(dt.parse("2020-04-22 00:00:00"), dt.parse("2011-02-12 00:00:00"), 241, 573, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(35, "note_3", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_3", null)))));
		examService.addExam(new Exam(dt.parse("2009-07-18 00:00:00"), dt.parse("2009-04-20 00:00:00"), 77, 233, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(39, "note_4", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_4", null)))));
		examService.addExam(new Exam(dt.parse("2006-06-11 00:00:00"), dt.parse("2014-01-03 00:00:00"), 63, 186, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(465, "note_5", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_5", null)))));
	}

	@After
	public void afterExam() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "exam", "subject_realization", "exam_type", "exam_realization", "exam_topic");
	}

	@Test
	public void getExam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/exam").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getExamId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/exam/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.points", equalTo(63)))
		.andExpect(jsonPath("$.durationInMinutes", equalTo(186)))
		.andExpect(jsonPath("$.subjectRealization", equalTo(null)))
		.andExpect(jsonPath("$.examType", equalTo(null))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2006-06-11 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.startTime")));
		assertEquals(dt.parse("2014-01-03 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.endTime")));

	}
}
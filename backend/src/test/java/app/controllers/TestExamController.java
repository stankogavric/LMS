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
import App.services.ExamService;

import App.models.ExamTopic;
import App.models.ExamRealization;
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
		examService.addExam(new Exam(dt.parse("2009-03-23 00:00:00"), dt.parse("2017-10-12 00:00:00"), 735, 971, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(350, "note_1", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_1", null)))));
		examService.addExam(new Exam(dt.parse("2011-06-00 00:00:00"), dt.parse("2007-03-24 00:00:00"), 461, 85, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(930, "note_2", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_2", null)))));
		examService.addExam(new Exam(dt.parse("2013-00-08 00:00:00"), dt.parse("2013-04-14 00:00:00"), 901, 446, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(221, "note_3", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_3", null)))));
		examService.addExam(new Exam(dt.parse("2017-04-07 00:00:00"), dt.parse("2025-03-09 00:00:00"), 344, 607, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(536, "note_4", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_4", null)))));
		examService.addExam(new Exam(dt.parse("2013-10-02 00:00:00"), dt.parse("2017-09-27 00:00:00"), 228, 982, null, null, new HashSet<ExamRealization>(Arrays.asList(new ExamRealization(786, "note_5", null, null))), new HashSet<ExamTopic>(Arrays.asList(new ExamTopic("description_5", null)))));
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
		.andExpect(jsonPath("$.points", equalTo(228)))
		.andExpect(jsonPath("$.durationInMinutes", equalTo(982)))
		.andExpect(jsonPath("$.subjectRealization", equalTo(null)))
		.andExpect(jsonPath("$.examType", equalTo(null))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2013-10-02 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.startTime")));
		assertEquals(dt.parse("2017-09-27 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.endTime")));

	}
}
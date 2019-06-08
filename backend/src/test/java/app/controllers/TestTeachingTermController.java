package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


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
import App.services.TeachingTermService;

import App.models.TeachingTerm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTeachingTermController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TeachingTermService teachingTermService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTeachingTerm() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		teachingTermService.addTeachingTerm(new TeachingTerm("day_1", dt.parse("2012-08-27 00:00:00"), dt.parse("2011-08-12 00:00:00"), null, null, false));
		teachingTermService.addTeachingTerm(new TeachingTerm("day_2", dt.parse("2005-08-26 00:00:00"), dt.parse("2011-00-14 00:00:00"), null, null, false));
		teachingTermService.addTeachingTerm(new TeachingTerm("day_3", dt.parse("2011-09-24 00:00:00"), dt.parse("2018-06-05 00:00:00"), null, null, false));
		teachingTermService.addTeachingTerm(new TeachingTerm("day_4", dt.parse("2013-08-28 00:00:00"), dt.parse("2012-07-00 00:00:00"), null, null, false));
		teachingTermService.addTeachingTerm(new TeachingTerm("day_5", dt.parse("2016-09-08 00:00:00"), dt.parse("2006-08-27 00:00:00"), null, null, false));
	}

	@After
	public void afterTeachingTerm() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "teaching_term", "subject_realization", "classroom");
	}

	@Test
	public void getTeachingTerm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/teachingterm").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTeachingTermId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/teachingterm/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.day", equalTo("day_5")))
		.andExpect(jsonPath("$.subjectRealization", equalTo(null)))
		.andExpect(jsonPath("$.classroom", equalTo(null)))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2016-09-08 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.startTime")));
		assertEquals(dt.parse("2006-08-27 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.endTime")));

	}
}
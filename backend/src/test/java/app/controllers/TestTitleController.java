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
import java.util.TimeZone;
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
import App.services.TitleService;

import App.models.Teacher;
import App.models.ScientificField;
import App.models.Title;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTitleController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TitleService titleService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTitle() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		titleService.addTitle(new Title(dt.parse("2007-08-16 00:00:00"), dt.parse("2012-00-15 00:00:00"), new ScientificField("name_1"), new Teacher("biography_1", null, null, null, null, false)));
		titleService.addTitle(new Title(dt.parse("2025-09-23 00:00:00"), dt.parse("2015-01-11 00:00:00"), new ScientificField("name_2"), new Teacher("biography_2", null, null, null, null, false)));
		titleService.addTitle(new Title(dt.parse("2014-10-04 00:00:00"), dt.parse("2009-08-12 00:00:00"), new ScientificField("name_3"), new Teacher("biography_3", null, null, null, null, false)));
		titleService.addTitle(new Title(dt.parse("2012-00-20 00:00:00"), dt.parse("2007-00-22 00:00:00"), new ScientificField("name_4"), new Teacher("biography_4", null, null, null, null, false)));
		titleService.addTitle(new Title(dt.parse("2025-11-06 00:00:00"), dt.parse("2005-05-02 00:00:00"), new ScientificField("name_5"), new Teacher("biography_5", null, null, null, null, false)));
	}

	@After
	public void afterTitle() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "title", "scientific_field", "teacher");
	}

	@Test
	public void getTitle() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/title").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTitleId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/title/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.scientificField.name", equalTo("name_5")))
		.andExpect(jsonPath("$.teacher.biography", equalTo("biography_5")))
		.andExpect(jsonPath("$.teacher.address", equalTo(null)))
		.andExpect(jsonPath("$.teacher.accountData", equalTo(null)))
		.andExpect(jsonPath("$.teacher.personalData", equalTo(null)))
		.andExpect(jsonPath("$.teacher.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2025-11-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.startDate")));
		assertEquals(dt.parse("2005-05-02 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.endDate")));

	}
}
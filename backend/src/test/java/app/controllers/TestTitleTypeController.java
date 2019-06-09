package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


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
import App.services.TitleTypeService;

import App.models.TitleType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTitleTypeController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TitleTypeService titleTypeService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTitleType() {
		titleTypeService.addTitleType(new TitleType("name_1"));
		titleTypeService.addTitleType(new TitleType("name_2"));
		titleTypeService.addTitleType(new TitleType("name_3"));
		titleTypeService.addTitleType(new TitleType("name_4"));
		titleTypeService.addTitleType(new TitleType("name_5"));
	}

	@After
	public void afterTitleType() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "title_type");
	}

	@Test
	public void getTitleType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/titletype").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTitleTypeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/titletype/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")));
	}
}
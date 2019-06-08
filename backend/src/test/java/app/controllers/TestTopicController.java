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
import App.services.TopicService;

import App.models.Topic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTopicController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TopicService topicService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTopic() {
		topicService.addTopic(new Topic("description_1", 167, "iconPath_1", null, false));
		topicService.addTopic(new Topic("description_2", 224, "iconPath_2", null, false));
		topicService.addTopic(new Topic("description_3", 567, "iconPath_3", null, false));
		topicService.addTopic(new Topic("description_4", 671, "iconPath_4", null, false));
		topicService.addTopic(new Topic("description_5", 375, "iconPath_5", null, false));
	}

	@After
	public void afterTopic() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "topic", "subject");
	}

	@Test
	public void getTopic() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTopicId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/topic/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.description", equalTo("description_5")))
		.andExpect(jsonPath("$.week", equalTo(375)))
		.andExpect(jsonPath("$.iconPath", equalTo("iconPath_5")))
		.andExpect(jsonPath("$.subject", equalTo(null)))
		.andExpect(jsonPath("$.deleted", equalTo(false)));
	}
}
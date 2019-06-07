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
import App.services.ExamTopicService;

import App.models.ExamTopic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestExamTopicController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ExamTopicService examTopicService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupExamTopic() {
		examTopicService.addExamTopic(new ExamTopic("description_1", null));
		examTopicService.addExamTopic(new ExamTopic("description_2", null));
		examTopicService.addExamTopic(new ExamTopic("description_3", null));
		examTopicService.addExamTopic(new ExamTopic("description_4", null));
		examTopicService.addExamTopic(new ExamTopic("description_5", null));
	}

	@After
	public void afterExamTopic() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "exam_topic", "exam");
	}

	@Test
	public void getExamTopic() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/examTopic").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getExamTopicId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/examTopic/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.description", equalTo("description_5")))
		.andExpect(jsonPath("$.exam", equalTo(null)));
	}
}
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
import App.services.PostFileService;

import App.models.Post;
import App.models.PostFile;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestPostFileController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PostFileService postFileService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupPostFile() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		postFileService.addPostFile(new PostFile("description_1", "url_1", new Post(dt.parse("2022-07-00 00:00:00"), "content_1", false, null, null, null)));
		postFileService.addPostFile(new PostFile("description_2", "url_2", new Post(dt.parse("2018-05-24 00:00:00"), "content_2", false, null, null, null)));
		postFileService.addPostFile(new PostFile("description_3", "url_3", new Post(dt.parse("2025-06-01 00:00:00"), "content_3", false, null, null, null)));
		postFileService.addPostFile(new PostFile("description_4", "url_4", new Post(dt.parse("2020-10-18 00:00:00"), "content_4", false, null, null, null)));
		postFileService.addPostFile(new PostFile("description_5", "url_5", new Post(dt.parse("2020-07-23 00:00:00"), "content_5", false, null, null, null)));
	}

	@After
	public void afterPostFile() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "post_file", "post");
	}

	@Test
	public void getPostFile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/postfile").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getPostFileId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/postfile/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.description", equalTo("description_5")))
		.andExpect(jsonPath("$.url", equalTo("url_5")))
		.andExpect(jsonPath("$.post.content", equalTo("content_5")))
		.andExpect(jsonPath("$.post.deleted", equalTo(false)))
		.andExpect(jsonPath("$.post.author", equalTo(null)))
		.andExpect(jsonPath("$.post.topic", equalTo(null))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2020-07-23 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.post.timeOfPublication")));

	}
}
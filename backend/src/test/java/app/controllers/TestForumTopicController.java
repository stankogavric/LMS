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
import App.services.ForumTopicService;

import App.models.ForumTopic;
import App.models.Post;
import App.models.ForumUser;
import App.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestForumTopicController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForumTopicService forumTopicService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupForumTopic() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		forumTopicService.addForumTopic(new ForumTopic("title_1", new Category("title_1", false, null, null, null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2020-06-15 00:00:00"), "content_1", false, null, null, null))), new ForumUser(null, null, null, null, null), false, false));
		forumTopicService.addForumTopic(new ForumTopic("title_2", new Category("title_2", false, null, null, null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2024-08-04 00:00:00"), "content_2", false, null, null, null))), new ForumUser(null, null, null, null, null), false, false));
		forumTopicService.addForumTopic(new ForumTopic("title_3", new Category("title_3", false, null, null, null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2010-03-23 00:00:00"), "content_3", false, null, null, null))), new ForumUser(null, null, null, null, null), false, false));
		forumTopicService.addForumTopic(new ForumTopic("title_4", new Category("title_4", false, null, null, null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2019-03-00 00:00:00"), "content_4", false, null, null, null))), new ForumUser(null, null, null, null, null), false, false));
		forumTopicService.addForumTopic(new ForumTopic("title_5", new Category("title_5", false, null, null, null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2011-06-11 00:00:00"), "content_5", false, null, null, null))), new ForumUser(null, null, null, null, null), false, false));
	}

	@After
	public void afterForumTopic() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "forum_topic", "category", "post", "forum_user");
	}

	@Test
	public void getForumTopic() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forumtopic").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getForumTopicId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/forumtopic/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.title", equalTo("title_5")))
		.andExpect(jsonPath("$.category.title", equalTo("title_5")))
		.andExpect(jsonPath("$.category.deleted", equalTo(false)))
		.andExpect(jsonPath("$.category.forum", equalTo(null)))
		.andExpect(jsonPath("$.author.account", equalTo(null)))
		.andExpect(jsonPath("$.author.forum", equalTo(null)))
		.andExpect(jsonPath("$.deleted", equalTo(false)))
		.andExpect(jsonPath("$.isLocked", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2011-06-11 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.posts[0].timeOfPublication")));

	}
}
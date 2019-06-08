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
import App.services.PostService;

import App.models.ForumTopic;
import App.models.Post;
import App.models.PostFile;
import App.models.ForumUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestPostController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PostService postService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupPost() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		postService.addPost(new Post(dt.parse("2023-08-28 00:00:00"), "content_1", false, new HashSet<PostFile>(Arrays.asList(new PostFile("description_1", "url_1", null))), new ForumUser(null, null, null, null, null), new ForumTopic("title_1", null, null, null, false, false)));
		postService.addPost(new Post(dt.parse("2018-06-23 00:00:00"), "content_2", false, new HashSet<PostFile>(Arrays.asList(new PostFile("description_2", "url_2", null))), new ForumUser(null, null, null, null, null), new ForumTopic("title_2", null, null, null, false, false)));
		postService.addPost(new Post(dt.parse("2005-01-11 00:00:00"), "content_3", false, new HashSet<PostFile>(Arrays.asList(new PostFile("description_3", "url_3", null))), new ForumUser(null, null, null, null, null), new ForumTopic("title_3", null, null, null, false, false)));
		postService.addPost(new Post(dt.parse("2023-07-00 00:00:00"), "content_4", false, new HashSet<PostFile>(Arrays.asList(new PostFile("description_4", "url_4", null))), new ForumUser(null, null, null, null, null), new ForumTopic("title_4", null, null, null, false, false)));
		postService.addPost(new Post(dt.parse("2025-11-25 00:00:00"), "content_5", false, new HashSet<PostFile>(Arrays.asList(new PostFile("description_5", "url_5", null))), new ForumUser(null, null, null, null, null), new ForumTopic("title_5", null, null, null, false, false)));
	}

	@After
	public void afterPost() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "post", "post_file", "forum_user", "forum_topic");
	}

	@Test
	public void getPost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/post").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getPostId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/post/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.content", equalTo("content_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false)))
		.andExpect(jsonPath("$.author.account", equalTo(null)))
		.andExpect(jsonPath("$.author.forum", equalTo(null)))
		.andExpect(jsonPath("$.topic.title", equalTo("title_5")))
		.andExpect(jsonPath("$.topic.category", equalTo(null)))
		.andExpect(jsonPath("$.topic.author", equalTo(null)))
		.andExpect(jsonPath("$.topic.deleted", equalTo(false)))
		.andExpect(jsonPath("$.topic.isLocked", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2025-11-25 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.timeOfPublication")));

	}
}
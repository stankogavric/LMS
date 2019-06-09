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
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import App.services.ForumUserService;

import App.models.ForumUserForumRole;
import App.models.Post;
import App.models.Forum;
import App.models.ForumUser;
import App.models.ForumTopic;
import App.models.AccountData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestForumUserController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForumUserService forumUserService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupForumUser() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		forumUserService.addForumUser(new ForumUser(new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new AccountData("username_1", "password_1", "email_1", null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2020-03-04 00:00:00"), "content_1", false, null, null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_1", null, null, null, false, false))), new Forum(null, null)));
		forumUserService.addForumUser(new ForumUser(new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new AccountData("username_2", "password_2", "email_2", null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2014-07-06 00:00:00"), "content_2", false, null, null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_2", null, null, null, false, false))), new Forum(null, null)));
		forumUserService.addForumUser(new ForumUser(new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new AccountData("username_3", "password_3", "email_3", null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2013-08-11 00:00:00"), "content_3", false, null, null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_3", null, null, null, false, false))), new Forum(null, null)));
		forumUserService.addForumUser(new ForumUser(new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new AccountData("username_4", "password_4", "email_4", null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2005-06-16 00:00:00"), "content_4", false, null, null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_4", null, null, null, false, false))), new Forum(null, null)));
		forumUserService.addForumUser(new ForumUser(new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new AccountData("username_5", "password_5", "email_5", null), new HashSet<Post>(Arrays.asList(new Post(dt.parse("2006-03-21 00:00:00"), "content_5", false, null, null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_5", null, null, null, false, false))), new Forum(null, null)));
	}

	@After
	public void afterForumUser() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "forum_user", "forum_user_forum_role", "account_data", "post", "forum_topic", "forum");
	}

	@Test
	public void getForumUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forumuser").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getForumUserId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/forumuser/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.account.username", equalTo("username_5")))
		.andExpect(jsonPath("$.account.email", equalTo("email_5"))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2006-03-21 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.posts[0].timeOfPublication")));
		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.account.password")));

	}
}
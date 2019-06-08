package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashSet;

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
import App.services.ForumService;

import App.models.Forum;
import App.models.ForumUser;
import App.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestForumController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForumService forumService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupForum() {
		forumService.addForum(new Forum(new HashSet<ForumUser>(Arrays.asList(new ForumUser(null, null, null, null, null))), new HashSet<Category>(Arrays.asList(new Category("title_1", false, null, null, null)))));
		forumService.addForum(new Forum(new HashSet<ForumUser>(Arrays.asList(new ForumUser(null, null, null, null, null))), new HashSet<Category>(Arrays.asList(new Category("title_2", false, null, null, null)))));
		forumService.addForum(new Forum(new HashSet<ForumUser>(Arrays.asList(new ForumUser(null, null, null, null, null))), new HashSet<Category>(Arrays.asList(new Category("title_3", false, null, null, null)))));
		forumService.addForum(new Forum(new HashSet<ForumUser>(Arrays.asList(new ForumUser(null, null, null, null, null))), new HashSet<Category>(Arrays.asList(new Category("title_4", false, null, null, null)))));
		forumService.addForum(new Forum(new HashSet<ForumUser>(Arrays.asList(new ForumUser(null, null, null, null, null))), new HashSet<Category>(Arrays.asList(new Category("title_5", false, null, null, null)))));
	}

	@After
	public void afterForum() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "forum", "forum_user", "category");
	}

	@Test
	public void getForum() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forum").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getForumId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forum/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));
	}
}
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
import App.services.ForumRoleService;

import App.models.ForumUserForumRole;
import App.models.ForumRole;
import App.models.CategoryRole;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestForumRoleController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForumRoleService forumRoleService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupForumRole() {
		forumRoleService.addForumRole(new ForumRole("title_1", new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_1", "couldWrite_1", null, null)))));
		forumRoleService.addForumRole(new ForumRole("title_2", new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_2", "couldWrite_2", null, null)))));
		forumRoleService.addForumRole(new ForumRole("title_3", new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_3", "couldWrite_3", null, null)))));
		forumRoleService.addForumRole(new ForumRole("title_4", new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_4", "couldWrite_4", null, null)))));
		forumRoleService.addForumRole(new ForumRole("title_5", new HashSet<ForumUserForumRole>(Arrays.asList(new ForumUserForumRole(null, null))), new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_5", "couldWrite_5", null, null)))));
	}

	@After
	public void afterForumRole() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "forum_role", "forum_user_forum_role", "category_role");
	}

	@Test
	public void getForumRole() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forumrole").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getForumRoleId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forumrole/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.title", equalTo("title_5")));
	}
}
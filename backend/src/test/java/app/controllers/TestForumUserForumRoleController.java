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
import App.services.ForumUserForumRoleService;

import App.models.ForumUserForumRole;
import App.models.ForumRole;
import App.models.ForumUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestForumUserForumRoleController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ForumUserForumRoleService forumUserForumRoleService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupForumUserForumRole() {
		forumUserForumRoleService.addForumUserForumRole(new ForumUserForumRole(new ForumUser(null, null, null, null, null), new ForumRole("title_1", null, null)));
		forumUserForumRoleService.addForumUserForumRole(new ForumUserForumRole(new ForumUser(null, null, null, null, null), new ForumRole("title_2", null, null)));
		forumUserForumRoleService.addForumUserForumRole(new ForumUserForumRole(new ForumUser(null, null, null, null, null), new ForumRole("title_3", null, null)));
		forumUserForumRoleService.addForumUserForumRole(new ForumUserForumRole(new ForumUser(null, null, null, null, null), new ForumRole("title_4", null, null)));
		forumUserForumRoleService.addForumUserForumRole(new ForumUserForumRole(new ForumUser(null, null, null, null, null), new ForumRole("title_5", null, null)));
	}

	@After
	public void afterForumUserForumRole() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "forum_user_forum_role", "forum_user", "forum_role");
	}

	@Test
	public void getForumUserForumRole() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forumuserforumrole").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getForumUserForumRoleId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forumuserforumrole/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.user.account", equalTo(null)))
		.andExpect(jsonPath("$.user.forum", equalTo(null)))
		.andExpect(jsonPath("$.role.title", equalTo("title_5")));
	}
}
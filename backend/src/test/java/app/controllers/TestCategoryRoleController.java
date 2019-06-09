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
import App.services.CategoryRoleService;

import App.models.Category;
import App.models.ForumRole;
import App.models.CategoryRole;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCategoryRoleController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CategoryRoleService categoryRoleService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupCategoryRole() {
		categoryRoleService.addCategoryRole(new CategoryRole("couldRead_1", "couldWrite_1", new Category("title_1", false, null, null, null), new ForumRole("title_1", null, null)));
		categoryRoleService.addCategoryRole(new CategoryRole("couldRead_2", "couldWrite_2", new Category("title_2", false, null, null, null), new ForumRole("title_2", null, null)));
		categoryRoleService.addCategoryRole(new CategoryRole("couldRead_3", "couldWrite_3", new Category("title_3", false, null, null, null), new ForumRole("title_3", null, null)));
		categoryRoleService.addCategoryRole(new CategoryRole("couldRead_4", "couldWrite_4", new Category("title_4", false, null, null, null), new ForumRole("title_4", null, null)));
		categoryRoleService.addCategoryRole(new CategoryRole("couldRead_5", "couldWrite_5", new Category("title_5", false, null, null, null), new ForumRole("title_5", null, null)));
	}

	@After
	public void afterCategoryRole() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "category_role", "category", "forum_role");
	}

	@Test
	public void getCategoryRole() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/categoryrole").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getCategoryRoleId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categoryrole/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.couldRead", equalTo("couldRead_5")))
		.andExpect(jsonPath("$.couldWrite", equalTo("couldWrite_5")))
		.andExpect(jsonPath("$.category.title", equalTo("title_5")))
		.andExpect(jsonPath("$.category.deleted", equalTo(false)))
		.andExpect(jsonPath("$.category.forum", equalTo(null)))
		.andExpect(jsonPath("$.forumRole.title", equalTo("title_5")));
	}
}
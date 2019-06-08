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
import App.services.CategoryService;

import App.models.ForumTopic;
import App.models.Forum;
import App.models.CategoryRole;
import App.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCategoryController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CategoryService categoryService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupCategory() {
		categoryService.addCategory(new Category("title_1", false, new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_1", "couldWrite_1", null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_1", null, null, null, false, false))), new Forum(null, null)));
		categoryService.addCategory(new Category("title_2", false, new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_2", "couldWrite_2", null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_2", null, null, null, false, false))), new Forum(null, null)));
		categoryService.addCategory(new Category("title_3", false, new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_3", "couldWrite_3", null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_3", null, null, null, false, false))), new Forum(null, null)));
		categoryService.addCategory(new Category("title_4", false, new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_4", "couldWrite_4", null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_4", null, null, null, false, false))), new Forum(null, null)));
		categoryService.addCategory(new Category("title_5", false, new HashSet<CategoryRole>(Arrays.asList(new CategoryRole("couldRead_5", "couldWrite_5", null, null))), new HashSet<ForumTopic>(Arrays.asList(new ForumTopic("title_5", null, null, null, false, false))), new Forum(null, null)));
	}

	@After
	public void afterCategory() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "category", "category_role", "forum_topic", "forum");
	}

	@Test
	public void getCategory() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/category").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getCategoryId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.title", equalTo("title_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false)));
	}
}
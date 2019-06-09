package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import com.jayway.jsonpath.JsonPath;
import org.springframework.security.crypto.bcrypt.BCrypt;
import static org.junit.Assert.assertEquals;
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
import App.services.AdministratorService;

import App.models.Administrator;
import App.models.AccountData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestAdministratorController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AdministratorService administratorService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupAdministrator() {
		administratorService.addAdministrator(new Administrator(new AccountData("username_1", "password_1", "email_1", null), false));
		administratorService.addAdministrator(new Administrator(new AccountData("username_2", "password_2", "email_2", null), false));
		administratorService.addAdministrator(new Administrator(new AccountData("username_3", "password_3", "email_3", null), false));
		administratorService.addAdministrator(new Administrator(new AccountData("username_4", "password_4", "email_4", null), false));
		administratorService.addAdministrator(new Administrator(new AccountData("username_5", "password_5", "email_5", null), false));
	}

	@After
	public void afterAdministrator() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "administrator", "account_data");
	}

	@Test
	public void getAdministrator() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/administrator").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getAdministratorId() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/administrator/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.accountData.username", equalTo("username_5")))
		.andExpect(jsonPath("$.accountData.email", equalTo("email_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.accountData.password")));

	}
}
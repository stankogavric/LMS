package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashSet;

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
import App.services.AccountDataService;

import App.models.AccountDataPermission;
import App.models.AccountData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestAccountDataController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountDataService accountDataService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupAccountData() {
		accountDataService.addAccountData(new AccountData("username_1", "password_1", "email_1", new HashSet<AccountDataPermission>(Arrays.asList(new AccountDataPermission(null, null)))));
		accountDataService.addAccountData(new AccountData("username_2", "password_2", "email_2", new HashSet<AccountDataPermission>(Arrays.asList(new AccountDataPermission(null, null)))));
		accountDataService.addAccountData(new AccountData("username_3", "password_3", "email_3", new HashSet<AccountDataPermission>(Arrays.asList(new AccountDataPermission(null, null)))));
		accountDataService.addAccountData(new AccountData("username_4", "password_4", "email_4", new HashSet<AccountDataPermission>(Arrays.asList(new AccountDataPermission(null, null)))));
		accountDataService.addAccountData(new AccountData("username_5", "password_5", "email_5", new HashSet<AccountDataPermission>(Arrays.asList(new AccountDataPermission(null, null)))));
	}

	@After
	public void afterAccountData() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "account_data", "account_data_permission");
	}

	@Test
	public void getAccountData() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/accountdata").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getAccountDataId() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/accountdata/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.username", equalTo("username_5")))
		.andExpect(jsonPath("$.email", equalTo("email_5"))).andReturn().getResponse().getContentAsString();


		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.password")));

	}
}
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
import App.services.AdministrativeStaffService;

import App.models.PersonalData;
import App.models.Address;
import App.models.AdministrativeStaff;
import App.models.AccountData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestAdministrativeStaffController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AdministrativeStaffService administrativeStaffService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupAdministrativeStaff() {
		administrativeStaffService.addAdministrativeStaff(new AdministrativeStaff(new Address("street_1", "number_1", null), new AccountData("username_1", "password_1", "email_1", null), new PersonalData("firstName_1", "lastName_1", "personalNumber_1", "profilePicturePath_1"), false));
		administrativeStaffService.addAdministrativeStaff(new AdministrativeStaff(new Address("street_2", "number_2", null), new AccountData("username_2", "password_2", "email_2", null), new PersonalData("firstName_2", "lastName_2", "personalNumber_2", "profilePicturePath_2"), false));
		administrativeStaffService.addAdministrativeStaff(new AdministrativeStaff(new Address("street_3", "number_3", null), new AccountData("username_3", "password_3", "email_3", null), new PersonalData("firstName_3", "lastName_3", "personalNumber_3", "profilePicturePath_3"), false));
		administrativeStaffService.addAdministrativeStaff(new AdministrativeStaff(new Address("street_4", "number_4", null), new AccountData("username_4", "password_4", "email_4", null), new PersonalData("firstName_4", "lastName_4", "personalNumber_4", "profilePicturePath_4"), false));
		administrativeStaffService.addAdministrativeStaff(new AdministrativeStaff(new Address("street_5", "number_5", null), new AccountData("username_5", "password_5", "email_5", null), new PersonalData("firstName_5", "lastName_5", "personalNumber_5", "profilePicturePath_5"), false));
	}

	@After
	public void afterAdministrativeStaff() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "administrative_staff", "address", "account_data", "personal_data");
	}

	@Test
	public void getAdministrativeStaff() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/administrativestaff").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getAdministrativeStaffId() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/administrativestaff/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.address.street", equalTo("street_5")))
		.andExpect(jsonPath("$.address.number", equalTo("number_5")))
		.andExpect(jsonPath("$.address.city", equalTo(null)))
		.andExpect(jsonPath("$.AccountData.username", equalTo("username_5")))
		.andExpect(jsonPath("$.AccountData.email", equalTo("email_5")))
		.andExpect(jsonPath("$.personalData.firstName", equalTo("firstName_5")))
		.andExpect(jsonPath("$.personalData.lastName", equalTo("lastName_5")))
		.andExpect(jsonPath("$.personalData.personalNumber", equalTo("personalNumber_5")))
		.andExpect(jsonPath("$.personalData.profilePicturePath", equalTo("profilePicturePath_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.AccountData.password")));

	}
}
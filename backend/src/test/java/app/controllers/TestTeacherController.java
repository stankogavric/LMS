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
import App.services.TeacherService;

import App.models.Teacher;
import App.models.Title;
import App.models.PersonalData;
import App.models.Address;
import App.models.AccountData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestTeacherController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TeacherService teacherService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupTeacher() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		teacherService.addTeacher(new Teacher("biography_1", new HashSet<Title>(Arrays.asList(new Title(dt.parse("2014-01-01 00:00:00"), dt.parse("2020-09-11 00:00:00"), null, null))), new Address("street_1", "number_1", null), new AccountData("username_1", "password_1", "email_1", null), new PersonalData("firstName_1", "lastName_1", "personalNumber_1", "profilePicturePath_1"), false));
		teacherService.addTeacher(new Teacher("biography_2", new HashSet<Title>(Arrays.asList(new Title(dt.parse("2007-02-10 00:00:00"), dt.parse("2022-09-05 00:00:00"), null, null))), new Address("street_2", "number_2", null), new AccountData("username_2", "password_2", "email_2", null), new PersonalData("firstName_2", "lastName_2", "personalNumber_2", "profilePicturePath_2"), false));
		teacherService.addTeacher(new Teacher("biography_3", new HashSet<Title>(Arrays.asList(new Title(dt.parse("2020-08-10 00:00:00"), dt.parse("2005-05-15 00:00:00"), null, null))), new Address("street_3", "number_3", null), new AccountData("username_3", "password_3", "email_3", null), new PersonalData("firstName_3", "lastName_3", "personalNumber_3", "profilePicturePath_3"), false));
		teacherService.addTeacher(new Teacher("biography_4", new HashSet<Title>(Arrays.asList(new Title(dt.parse("2019-10-12 00:00:00"), dt.parse("2017-06-12 00:00:00"), null, null))), new Address("street_4", "number_4", null), new AccountData("username_4", "password_4", "email_4", null), new PersonalData("firstName_4", "lastName_4", "personalNumber_4", "profilePicturePath_4"), false));
		teacherService.addTeacher(new Teacher("biography_5", new HashSet<Title>(Arrays.asList(new Title(dt.parse("2005-11-11 00:00:00"), dt.parse("2008-11-26 00:00:00"), null, null))), new Address("street_5", "number_5", null), new AccountData("username_5", "password_5", "email_5", null), new PersonalData("firstName_5", "lastName_5", "personalNumber_5", "profilePicturePath_5"), false));
	}

	@After
	public void afterTeacher() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "teacher", "title", "address", "account_data", "personal_data");
	}

	@Test
	public void getTeacher() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/teacher").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getTeacherId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/teacher/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.biography", equalTo("biography_5")))
		.andExpect(jsonPath("$.address.street", equalTo("street_5")))
		.andExpect(jsonPath("$.address.number", equalTo("number_5")))
		.andExpect(jsonPath("$.address.city", equalTo(null)))
		.andExpect(jsonPath("$.accountData.username", equalTo("username_5")))
		.andExpect(jsonPath("$.accountData.email", equalTo("email_5")))
		.andExpect(jsonPath("$.personalData.firstName", equalTo("firstName_5")))
		.andExpect(jsonPath("$.personalData.lastName", equalTo("lastName_5")))
		.andExpect(jsonPath("$.personalData.personalNumber", equalTo("personalNumber_5")))
		.andExpect(jsonPath("$.personalData.profilePicturePath", equalTo("profilePicturePath_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2005-11-11 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.titles[0].startDate")));
		assertEquals(dt.parse("2008-11-26 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.titles[0].endDate")));
		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.accountData.password")));

	}
}
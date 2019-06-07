package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import com.jayway.jsonpath.JsonPath;import java.util.Arrays;
import java.util.HashSet;

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
import App.services.StudentService;

import App.models.Address;
import App.models.SubjectAttendance;
import App.models.AccountData;
import App.models.StudentYear;
import App.models.Student;
import App.models.PersonalData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestStudentController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	StudentService studentService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupStudent() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		studentService.addStudent(new Student(new HashSet<SubjectAttendance>(Arrays.asList(new SubjectAttendance(982, null, null))), new Address("street_1", "number_1", null), new HashSet<StudentYear>(Arrays.asList(new StudentYear(dt.parse("2010-11-00 00:00:00"), "numIndex_1", null, null, null))), new AccountData("username_1", "password_1", "email_1", null), new PersonalData("firstName_1", "lastName_1", "personalNumber_1", "profilePicturePath_1"), false, 813));
		studentService.addStudent(new Student(new HashSet<SubjectAttendance>(Arrays.asList(new SubjectAttendance(225, null, null))), new Address("street_2", "number_2", null), new HashSet<StudentYear>(Arrays.asList(new StudentYear(dt.parse("2009-00-13 00:00:00"), "numIndex_2", null, null, null))), new AccountData("username_2", "password_2", "email_2", null), new PersonalData("firstName_2", "lastName_2", "personalNumber_2", "profilePicturePath_2"), false, 120));
		studentService.addStudent(new Student(new HashSet<SubjectAttendance>(Arrays.asList(new SubjectAttendance(998, null, null))), new Address("street_3", "number_3", null), new HashSet<StudentYear>(Arrays.asList(new StudentYear(dt.parse("2011-04-20 00:00:00"), "numIndex_3", null, null, null))), new AccountData("username_3", "password_3", "email_3", null), new PersonalData("firstName_3", "lastName_3", "personalNumber_3", "profilePicturePath_3"), false, 768));
		studentService.addStudent(new Student(new HashSet<SubjectAttendance>(Arrays.asList(new SubjectAttendance(811, null, null))), new Address("street_4", "number_4", null), new HashSet<StudentYear>(Arrays.asList(new StudentYear(dt.parse("2024-02-23 00:00:00"), "numIndex_4", null, null, null))), new AccountData("username_4", "password_4", "email_4", null), new PersonalData("firstName_4", "lastName_4", "personalNumber_4", "profilePicturePath_4"), false, 40));
		studentService.addStudent(new Student(new HashSet<SubjectAttendance>(Arrays.asList(new SubjectAttendance(26, null, null))), new Address("street_5", "number_5", null), new HashSet<StudentYear>(Arrays.asList(new StudentYear(dt.parse("2014-07-00 00:00:00"), "numIndex_5", null, null, null))), new AccountData("username_5", "password_5", "email_5", null), new PersonalData("firstName_5", "lastName_5", "personalNumber_5", "profilePicturePath_5"), false, 199));
	}

	@After
	public void afterStudent() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "student", "subject_attendance", "address", "student_year", "account_data", "personal_data");
	}

	@Test
	public void getStudent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/student").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getStudentId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/student/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.address.street", equalTo("street_5")))
		.andExpect(jsonPath("$.address.number", equalTo("number_5")))
		.andExpect(jsonPath("$.address.city", equalTo(null)))
		.andExpect(jsonPath("$.accountData.username", equalTo("username_5")))
		.andExpect(jsonPath("$.accountData.email", equalTo("email_5")))
		.andExpect(jsonPath("$.personalData.firstName", equalTo("firstName_5")))
		.andExpect(jsonPath("$.personalData.lastName", equalTo("lastName_5")))
		.andExpect(jsonPath("$.personalData.personalNumber", equalTo("personalNumber_5")))
		.andExpect(jsonPath("$.personalData.profilePicturePath", equalTo("profilePicturePath_5")))
		.andExpect(jsonPath("$.deleted", equalTo(false)))
		.andExpect(jsonPath("$.yearOfStudy", equalTo(199))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2014-07-00 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.studentYears[0].enrolmentDate")));
		assertEquals(true, BCrypt.checkpw("password_5", JsonPath.parse(result).read("$.accountData.password")));

	}
}
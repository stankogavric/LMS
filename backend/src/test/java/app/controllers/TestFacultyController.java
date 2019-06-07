package app.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;


import java.text.ParseException;
import com.jayway.jsonpath.JsonPath;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import java.util.TimeZone;import java.util.Arrays;
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
import App.services.FacultyService;

import App.models.Address;
import App.models.Teacher;
import App.models.University;
import App.models.FacultyEmails;
import App.models.Faculty;
import App.models.FacultyPhones;
import App.models.Classroom;
import App.models.StudyProgram;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestFacultyController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	FacultyService facultyService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupFaculty() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		facultyService.addFaculty(new Faculty("name_1", new University("name_1", dt.parse("2005-02-11 00:00:00"), null, null, null, "description_1", null, null), new Address("street_1", "number_1", null), new Teacher("biography_1", null, null, null, null, false), new HashSet<StudyProgram>(Arrays.asList(new StudyProgram("name_1", null, null, null, "description_1", false))), "description_1", new HashSet<FacultyPhones>(Arrays.asList(new FacultyPhones(null, null))), new HashSet<FacultyEmails>(Arrays.asList(new FacultyEmails(null, null))), new HashSet<Classroom>(Arrays.asList(new Classroom("name_1", "type_1", 91, null)))));
		facultyService.addFaculty(new Faculty("name_2", new University("name_2", dt.parse("2022-09-14 00:00:00"), null, null, null, "description_2", null, null), new Address("street_2", "number_2", null), new Teacher("biography_2", null, null, null, null, false), new HashSet<StudyProgram>(Arrays.asList(new StudyProgram("name_2", null, null, null, "description_2", false))), "description_2", new HashSet<FacultyPhones>(Arrays.asList(new FacultyPhones(null, null))), new HashSet<FacultyEmails>(Arrays.asList(new FacultyEmails(null, null))), new HashSet<Classroom>(Arrays.asList(new Classroom("name_2", "type_2", 199, null)))));
		facultyService.addFaculty(new Faculty("name_3", new University("name_3", dt.parse("2009-09-16 00:00:00"), null, null, null, "description_3", null, null), new Address("street_3", "number_3", null), new Teacher("biography_3", null, null, null, null, false), new HashSet<StudyProgram>(Arrays.asList(new StudyProgram("name_3", null, null, null, "description_3", false))), "description_3", new HashSet<FacultyPhones>(Arrays.asList(new FacultyPhones(null, null))), new HashSet<FacultyEmails>(Arrays.asList(new FacultyEmails(null, null))), new HashSet<Classroom>(Arrays.asList(new Classroom("name_3", "type_3", 125, null)))));
		facultyService.addFaculty(new Faculty("name_4", new University("name_4", dt.parse("2009-11-03 00:00:00"), null, null, null, "description_4", null, null), new Address("street_4", "number_4", null), new Teacher("biography_4", null, null, null, null, false), new HashSet<StudyProgram>(Arrays.asList(new StudyProgram("name_4", null, null, null, "description_4", false))), "description_4", new HashSet<FacultyPhones>(Arrays.asList(new FacultyPhones(null, null))), new HashSet<FacultyEmails>(Arrays.asList(new FacultyEmails(null, null))), new HashSet<Classroom>(Arrays.asList(new Classroom("name_4", "type_4", 337, null)))));
		facultyService.addFaculty(new Faculty("name_5", new University("name_5", dt.parse("2014-02-01 00:00:00"), null, null, null, "description_5", null, null), new Address("street_5", "number_5", null), new Teacher("biography_5", null, null, null, null, false), new HashSet<StudyProgram>(Arrays.asList(new StudyProgram("name_5", null, null, null, "description_5", false))), "description_5", new HashSet<FacultyPhones>(Arrays.asList(new FacultyPhones(null, null))), new HashSet<FacultyEmails>(Arrays.asList(new FacultyEmails(null, null))), new HashSet<Classroom>(Arrays.asList(new Classroom("name_5", "type_5", 516, null)))));
	}

	@After
	public void afterFaculty() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "faculty", "university", "address", "teacher", "study_program", "faculty_phones", "faculty_emails", "classroom");
	}

	@Test
	public void getFaculty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/faculty").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getFacultyId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/faculty/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.university.name", equalTo("name_5")))
		.andExpect(jsonPath("$.university.address", equalTo(null)))
		.andExpect(jsonPath("$.university.rector", equalTo(null)))
		.andExpect(jsonPath("$.university.description", equalTo("description_5")))
		.andExpect(jsonPath("$.address.street", equalTo("street_5")))
		.andExpect(jsonPath("$.address.number", equalTo("number_5")))
		.andExpect(jsonPath("$.address.city", equalTo(null)))
		.andExpect(jsonPath("$.dean.biography", equalTo("biography_5")))
		.andExpect(jsonPath("$.dean.address", equalTo(null)))
		.andExpect(jsonPath("$.dean.accountData", equalTo(null)))
		.andExpect(jsonPath("$.dean.personalData", equalTo(null)))
		.andExpect(jsonPath("$.dean.deleted", equalTo(false)))
		.andExpect(jsonPath("$.description", equalTo("description_5"))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2014-02-01 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.university.dateOfEstablishment")));

	}
}
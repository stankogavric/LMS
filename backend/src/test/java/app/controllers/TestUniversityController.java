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
import App.services.UniversityService;

import App.models.Faculty;
import App.models.University;
import App.models.Teacher;
import App.models.UniversityEmails;
import App.models.Address;
import App.models.UniversityPhones;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestUniversityController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UniversityService universityService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupUniversity() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		universityService.addUniversity(new University("name_1", dt.parse("2022-10-04 00:00:00"), new HashSet<Faculty>(Arrays.asList(new Faculty("name_1", null, null, null, null, "description_1", null, null, null))), new Address("street_1", "number_1", null), new Teacher("biography_1", null, null, null, null, false), "description_1", new HashSet<UniversityPhones>(Arrays.asList(new UniversityPhones(null, null))), new HashSet<UniversityEmails>(Arrays.asList(new UniversityEmails(null, null)))));
		universityService.addUniversity(new University("name_2", dt.parse("2017-11-16 00:00:00"), new HashSet<Faculty>(Arrays.asList(new Faculty("name_2", null, null, null, null, "description_2", null, null, null))), new Address("street_2", "number_2", null), new Teacher("biography_2", null, null, null, null, false), "description_2", new HashSet<UniversityPhones>(Arrays.asList(new UniversityPhones(null, null))), new HashSet<UniversityEmails>(Arrays.asList(new UniversityEmails(null, null)))));
		universityService.addUniversity(new University("name_3", dt.parse("2023-01-26 00:00:00"), new HashSet<Faculty>(Arrays.asList(new Faculty("name_3", null, null, null, null, "description_3", null, null, null))), new Address("street_3", "number_3", null), new Teacher("biography_3", null, null, null, null, false), "description_3", new HashSet<UniversityPhones>(Arrays.asList(new UniversityPhones(null, null))), new HashSet<UniversityEmails>(Arrays.asList(new UniversityEmails(null, null)))));
		universityService.addUniversity(new University("name_4", dt.parse("2021-01-09 00:00:00"), new HashSet<Faculty>(Arrays.asList(new Faculty("name_4", null, null, null, null, "description_4", null, null, null))), new Address("street_4", "number_4", null), new Teacher("biography_4", null, null, null, null, false), "description_4", new HashSet<UniversityPhones>(Arrays.asList(new UniversityPhones(null, null))), new HashSet<UniversityEmails>(Arrays.asList(new UniversityEmails(null, null)))));
		universityService.addUniversity(new University("name_5", dt.parse("2012-05-00 00:00:00"), new HashSet<Faculty>(Arrays.asList(new Faculty("name_5", null, null, null, null, "description_5", null, null, null))), new Address("street_5", "number_5", null), new Teacher("biography_5", null, null, null, null, false), "description_5", new HashSet<UniversityPhones>(Arrays.asList(new UniversityPhones(null, null))), new HashSet<UniversityEmails>(Arrays.asList(new UniversityEmails(null, null)))));
	}

	@After
	public void afterUniversity() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "university", "faculty", "address", "teacher", "university_phones", "university_emails");
	}

	@Test
	public void getUniversity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/university").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getUniversityId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/university/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.address.street", equalTo("street_5")))
		.andExpect(jsonPath("$.address.number", equalTo("number_5")))
		.andExpect(jsonPath("$.address.city", equalTo(null)))
		.andExpect(jsonPath("$.rector.biography", equalTo("biography_5")))
		.andExpect(jsonPath("$.rector.address", equalTo(null)))
		.andExpect(jsonPath("$.rector.accountData", equalTo(null)))
		.andExpect(jsonPath("$.rector.personalData", equalTo(null)))
		.andExpect(jsonPath("$.rector.deleted", equalTo(false)))
		.andExpect(jsonPath("$.description", equalTo("description_5"))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2012-05-00 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.dateOfEstablishment")));

	}
}
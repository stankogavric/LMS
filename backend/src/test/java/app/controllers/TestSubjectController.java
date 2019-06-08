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
import App.services.SubjectService;

import App.models.YearOfStudy;
import App.models.Topic;
import App.models.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestSubjectController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	SubjectService subjectService;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setupSubject() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		subjectService.addSubject(new Subject("name_1", 22, false, 460, 124, 553, 794, 256, new HashSet<Topic>(Arrays.asList(new Topic("description_1", 397, "iconPath_1", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 754, false, 717, 764, 525, 117, 9, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_1", 301, false, 682, 675, 245, 187, 972, null, null, null, null, false))), new YearOfStudy(465, dt.parse("2014-07-23 00:00:00"), dt.parse("2016-09-20 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_2", 544, false, 400, 722, 274, 341, 678, new HashSet<Topic>(Arrays.asList(new Topic("description_2", 788, "iconPath_2", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 989, false, 391, 875, 359, 156, 972, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_2", 640, false, 695, 370, 875, 927, 772, null, null, null, null, false))), new YearOfStudy(18, dt.parse("2018-10-23 00:00:00"), dt.parse("2010-04-19 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_3", 594, false, 566, 473, 483, 394, 315, new HashSet<Topic>(Arrays.asList(new Topic("description_3", 208, "iconPath_3", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 809, false, 409, 7, 670, 586, 994, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_3", 986, false, 395, 520, 283, 715, 734, null, null, null, null, false))), new YearOfStudy(584, dt.parse("2018-09-01 00:00:00"), dt.parse("2016-07-17 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_4", 920, false, 319, 739, 504, 643, 787, new HashSet<Topic>(Arrays.asList(new Topic("description_4", 264, "iconPath_4", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 62, false, 90, 160, 557, 564, 836, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_4", 616, false, 15, 863, 418, 227, 879, null, null, null, null, false))), new YearOfStudy(939, dt.parse("2017-04-05 00:00:00"), dt.parse("2009-05-17 00:00:00"), null, null), false));
		subjectService.addSubject(new Subject("name_5", 271, false, 436, 300, 30, 624, 382, new HashSet<Topic>(Arrays.asList(new Topic("description_5", 820, "iconPath_5", null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 763, false, 983, 176, 466, 464, 693, null, null, null, null, false))), new HashSet<Subject>(Arrays.asList(new Subject("name_5", 766, false, 626, 684, 432, 475, 766, null, null, null, null, false))), new YearOfStudy(564, dt.parse("2006-05-24 00:00:00"), dt.parse("2005-10-06 00:00:00"), null, null), false));
	}

	@After
	public void afterSubject() throws SQLException {
		DbTestUtil.resetAutoIncrementColumns(applicationContext, "subject", "topic", "subject", "subject", "year_of_study");
	}

	@Test
	public void getSubject() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/subject").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
    public void getSubjectId() throws Exception {
        SimpleDateFormat dtt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		dtt.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/subject/5").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.name", equalTo("name_5")))
		.andExpect(jsonPath("$.ects", equalTo(271)))
		.andExpect(jsonPath("$.mandatory", equalTo(false)))
		.andExpect(jsonPath("$.lecturesNum", equalTo(436)))
		.andExpect(jsonPath("$.exercisesNum", equalTo(300)))
		.andExpect(jsonPath("$.otherActivitiesNum", equalTo(30)))
		.andExpect(jsonPath("$.researchPaper", equalTo(624)))
		.andExpect(jsonPath("$.otherClasses", equalTo(382)))
		.andExpect(jsonPath("$.prerequisites[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisites[0].ects", equalTo(763)))
		.andExpect(jsonPath("$.prerequisites[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisites[0].lecturesNum", equalTo(983)))
		.andExpect(jsonPath("$.prerequisites[0].exercisesNum", equalTo(176)))
		.andExpect(jsonPath("$.prerequisites[0].otherActivitiesNum", equalTo(466)))
		.andExpect(jsonPath("$.prerequisites[0].researchPaper", equalTo(464)))
		.andExpect(jsonPath("$.prerequisites[0].otherClasses", equalTo(693)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisites[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].name", equalTo("name_5")))
		.andExpect(jsonPath("$.prerequisiteFor[0].ects", equalTo(766)))
		.andExpect(jsonPath("$.prerequisiteFor[0].mandatory", equalTo(false)))
		.andExpect(jsonPath("$.prerequisiteFor[0].lecturesNum", equalTo(626)))
		.andExpect(jsonPath("$.prerequisiteFor[0].exercisesNum", equalTo(684)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherActivitiesNum", equalTo(432)))
		.andExpect(jsonPath("$.prerequisiteFor[0].researchPaper", equalTo(475)))
		.andExpect(jsonPath("$.prerequisiteFor[0].otherClasses", equalTo(766)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisites", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].prerequisiteFor", equalTo(null)))
		.andExpect(jsonPath("$.prerequisiteFor[0].deleted", equalTo(false)))
		.andExpect(jsonPath("$.deleted", equalTo(false))).andReturn().getResponse().getContentAsString();

		assertEquals(dt.parse("2006-05-24 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.startDate")));
		assertEquals(dt.parse("2005-10-06 00:00:00"), dtt.parse(JsonPath.parse(result).read("$.yearOfStudy.endDate")));

	}
}
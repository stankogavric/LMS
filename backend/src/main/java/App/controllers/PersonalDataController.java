package App.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import App.models.PersonalData;
import App.services.PersonalDataService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/personaldata")
public class PersonalDataController {

	@Autowired
	PersonalDataService personalDataService;

	@JsonView(HideOptionalProperties.class)
	@RequestMapping()
	public ResponseEntity<Iterable<PersonalData>> getPersonalData() {
		return new ResponseEntity<Iterable<PersonalData>>(personalDataService.getPersonalData(), HttpStatus.OK);
	}

	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PersonalData> getPersonalDataById(@PathVariable Long id) {
		Optional<PersonalData> personalData = personalDataService.getPersonalDataById(id);
		if (personalData.isPresent()) {
			return new ResponseEntity<PersonalData>(personalData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<PersonalData>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public ResponseEntity<PersonalData> getPersonalDataByUsername(@PathVariable String username) {
		Optional<PersonalData> personalData = personalDataService.getPersonalDataByUsername(username);
		if (personalData.isPresent()) {
			return new ResponseEntity<PersonalData>(personalData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<PersonalData>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<PersonalData> addPersonalData(@RequestBody PersonalData PersonalData) {
		personalDataService.addPersonalData(PersonalData);
		return new ResponseEntity<PersonalData>(PersonalData, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PersonalData> updatePersonalData(@PathVariable Long id,
			@RequestBody PersonalData PersonalData) {
		personalDataService.updatePersonalData(id, PersonalData);
		return new ResponseEntity<PersonalData>(PersonalData, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PersonalData> removePersonalData(@PathVariable Long id) {
		try {
			personalDataService.removePersonalData(id);
		} catch (Exception e) {
			return new ResponseEntity<PersonalData>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PersonalData>(HttpStatus.NO_CONTENT);
	}

}

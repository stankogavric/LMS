package App.controllers;

import java.util.ArrayList;
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

import App.models.Email;
import App.models.Phone;
import App.models.University;
import App.services.UniversityService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<University>> getUniversities() {
        return new ResponseEntity<Iterable<University>>(universityService.getUniversities(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        Optional<University> university = universityService.getUniversityById(id);
        if(university.isPresent()) {
            return new ResponseEntity<University>(university.get(), HttpStatus.OK);
        }
        return new ResponseEntity<University>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<University> addUniversity(@RequestBody University Universities) {
        universityService.addUniversity(Universities);
        return new ResponseEntity<University>(Universities, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University Universities) {
        universityService.updateUniversity(id, Universities);
        return new ResponseEntity<University>(Universities, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<University> removeUniversity(@PathVariable Long id) {
        try {
            universityService.removeUniversity(id);
        }catch (Exception e) {
            return new ResponseEntity<University>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<University>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/phones/{universityId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Phone>> getUniversityPhones(@PathVariable Long universityId) {
        return new ResponseEntity<ArrayList<Phone>>(universityService.getUniversityPhones(universityId), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/emails/{universityId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Email>> getUniversityEmails(@PathVariable Long universityId) {
        return new ResponseEntity<ArrayList<Email>>(universityService.getUniversityEmails(universityId), HttpStatus.OK);
    }

}

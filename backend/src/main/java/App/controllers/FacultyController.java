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
import App.models.Faculty;
import App.models.Phone;
import App.models.StudyProgram;
import App.services.FacultyService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Faculty>> getFaculties() {
        return new ResponseEntity<Iterable<Faculty>>(facultyService.getFaculties(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.getFacultyById(id);
        if(faculty.isPresent()) {
            return new ResponseEntity<Faculty>(faculty.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Faculty>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty Faculties) {
        facultyService.addFaculty(Faculties);
        return new ResponseEntity<Faculty>(Faculties, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty Faculties) {
        facultyService.updateFaculty(id, Faculties);
        return new ResponseEntity<Faculty>(Faculties, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        try {
            facultyService.removeFaculty(id);
        }catch (Exception e) {
            return new ResponseEntity<Faculty>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Faculty>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/studyPrograms/{facultyId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<StudyProgram>> getStudyPrograms(@PathVariable Long facultyId) {
        return new ResponseEntity<ArrayList<StudyProgram>>(facultyService.getStudyPrograms(facultyId), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/phones/{facultyId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Phone>> getFacultyPhones(@PathVariable Long facultyId) {
        return new ResponseEntity<ArrayList<Phone>>(facultyService.getFacultyPhones(facultyId), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/emails/{facultyId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Email>> getFacultyEmails(@PathVariable Long facultyId) {
        return new ResponseEntity<ArrayList<Email>>(facultyService.getFacultyEmails(facultyId), HttpStatus.OK);
    }

}

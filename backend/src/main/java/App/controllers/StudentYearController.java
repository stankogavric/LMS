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

import App.models.StudentYear;
import App.services.StudentYearService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/studentyear")
public class StudentYearController {

    @Autowired
    StudentYearService studentYearService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<StudentYear>> getStudentsYear() {
        return new ResponseEntity<Iterable<StudentYear>>(studentYearService.getStudentsYear(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StudentYear> getStudentYearById(@PathVariable Long id) {
        Optional<StudentYear> studentYear = studentYearService.getStudentYearById(id);
        if(studentYear.isPresent()) {
            return new ResponseEntity<StudentYear>(studentYear.get(), HttpStatus.OK);
        }
        return new ResponseEntity<StudentYear>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<StudentYear> addStudentYear(@RequestBody StudentYear StudentsYear) {
        studentYearService.addStudentYear(StudentsYear);
        return new ResponseEntity<StudentYear>(StudentsYear, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<StudentYear> updateStudentYear(@PathVariable Long id, @RequestBody StudentYear StudentsYear) {
        studentYearService.updateStudentYear(id, StudentsYear);
        return new ResponseEntity<StudentYear>(StudentsYear, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<StudentYear> removeStudentYear(@PathVariable Long id) {
        try {
            studentYearService.removeStudentYear(id);
        }catch (Exception e) {
            return new ResponseEntity<StudentYear>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudentYear>(HttpStatus.NO_CONTENT);
    }

}

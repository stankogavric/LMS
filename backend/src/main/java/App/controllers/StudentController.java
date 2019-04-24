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

import App.models.Student;
import App.services.StudentService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Student>> getStudents() {
        return new ResponseEntity<Iterable<Student>>(studentService.getStudents(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@RequestBody Student Students) {
        studentService.addStudent(Students);
        return new ResponseEntity<Student>(Students, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student Students) {
        studentService.updateStudent(id, Students);
        return new ResponseEntity<Student>(Students, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        try {
            studentService.removeStudent(id);
        }catch (Exception e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<Student>>> getStudentsByName(@PathVariable String name) {
        Iterable<Optional<Student>> students = studentService.getStudentsByName(name);
        return new ResponseEntity<Iterable<Optional<Student>>>(students, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByJmbg/{jmbg}", method=RequestMethod.GET)
    public ResponseEntity<Student> getStudentByJmbg(@PathVariable String jmbg) {
        Optional<Student> student = studentService.getStudentByJmbg(jmbg);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

}

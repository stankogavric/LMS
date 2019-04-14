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

import App.models.Teacher;
import App.services.TeacherService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Teacher>> getTeachers() {
        return new ResponseEntity<Iterable<Teacher>>(teacherService.getTeachers(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if(teacher.isPresent()) {
            return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher Teachers) {
        teacherService.addTeacher(Teachers);
        return new ResponseEntity<Teacher>(Teachers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher Teachers) {
        teacherService.updateTeacher(id, Teachers);
        return new ResponseEntity<Teacher>(Teachers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Teacher> removeTeacher(@PathVariable Long id) {
        try {
            teacherService.removeTeacher(id);
        }catch (Exception e) {
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }

}

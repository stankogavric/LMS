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

import App.models.Classroom;
import App.services.ClassroomService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
	@Autowired
    ClassroomService classroomService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Classroom>> getClassrooms() {
        return new ResponseEntity<Iterable<Classroom>>(classroomService.getClassrooms(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        Optional<Classroom> classroom = classroomService.getClassroomById(id);
        if(classroom.isPresent()) {
            return new ResponseEntity<Classroom>(classroom.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Classroom>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Classroom> addClassroom(@RequestBody Classroom Classrooms) {
        classroomService.addClassroom(Classrooms);
        return new ResponseEntity<Classroom>(Classrooms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom Classrooms) {
        classroomService.updateClassroom(id, Classrooms);
        return new ResponseEntity<Classroom>(Classrooms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Classroom> removeClassroom(@PathVariable Long id) {
        try {
            classroomService.removeClassroom(id);
        }catch (Exception e) {
            return new ResponseEntity<Classroom>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Classroom>(HttpStatus.NO_CONTENT);
    }

}